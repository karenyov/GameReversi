package MultiplayerJogador1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.BufferOverflowException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Chat extends JPanel implements Runnable {

    /*
     * IO and UI elements
     */
    public JTextField enterField;
    private JTextArea displayArea;
    private PrintStream output;
    private BufferedReader input;
    private String message = "";
    private JScrollPane scroll;

    /*
     * Network Elements
     */
    private int serverPort;
    private ServerSocket server;
    private String playerName;
    private Socket connection;
    private JLabel lblNewLabel;

    public Chat(int serverPort, String playerName) {

        this.serverPort = serverPort;
        this.playerName = playerName;
        setLayout(null);

        displayArea = new JTextArea();
        displayArea.setRows(10);
        displayArea.setColumns(80);
        displayArea.setLineWrap(true);
        displayArea.setEditable(false);

        scroll = new JScrollPane(displayArea);
        scroll.setBounds(0, 0, 500, 160);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll);

        enterField = new JTextField();
        enterField.setBounds(0, 170, 300, 25);
        enterField.requestFocus();
        enterField.setEditable(false);

        enterField.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        sendData(event.getActionCommand());
                        enterField.setText("");
                    }
                });
        add(enterField);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("player1.png"));
        lblNewLabel.setBounds(55, 206, 369, 284);
        add(lblNewLabel);
    }
    
    private void showMessage(final String messageToDisplay)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        displayArea.append(messageToDisplay);
                    }
                }
        );
    }

    private void sendData(String message){
        try
        {
            output.println(this.playerName + ": " + message);
            output.flush();
            showMessage("\n" + this.playerName + ": " + message);
        }catch(BufferOverflowException e) {
            displayArea.append("\nErro ao enviar dados");
        }
    }


    private void waitConnection() throws IOException
    {
        showMessage("Esperando pelo oponente...\n");
         connection = server.accept();
        showMessage("Conexão estabelecida: " + connection.getInetAddress().getHostName()+"\n");
    }

    private void getIOdata() throws IOException
    {
        output = new PrintStream(connection.getOutputStream());
        input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }

    private void setTextFieldEditable(final boolean editable)
    {
        SwingUtilities.invokeLater(
            new Runnable()
            {
                public void run()
                {
                    enterField.setEditable(editable);
                }
            }
        );
    }

    private void manipulaDadosNaJanela() throws IOException
    {
        boolean tudoOk = true;

        String mensagem = " Conexão bem sucedida... ";
        showMessage(mensagem);
        setTextFieldEditable(true);

        do
        {
            try
            {
                mensagem = (String) input.readLine();
                showMessage("\n" + mensagem); //exibe a mensagem
            }
            catch(IOException e)
            {
                tudoOk = false;
                showMessage("\n Formato errado para mensagem.");

            }

        }while(tudoOk);
    }

    private void closeConnection()
    {
        showMessage("\n Fechando conexão \n");
        setTextFieldEditable(false);

        try
        {
            output.close();
            input.close();
            connection.close();
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }


    private void runServer(){
        try {
            server = new ServerSocket(this.serverPort, 1);
            while(true){
                try {
                    waitConnection();
                    getIOdata();
                    manipulaDadosNaJanela();
                } catch (EOFException eofexc) {
                    showMessage("\n Servidor fechou conexão");
                    eofexc.printStackTrace();
                }
                finally{
                    closeConnection();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }



    public void run() {
        runServer();
    }

}

