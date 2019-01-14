import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JPanel {

    public static void main(String[] args) throws Exception {

        JFrame f = new JFrame("Редактор слов");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout()); // Устанавливаем менеджер компоновки
        f.setPreferredSize(new Dimension(800, 600));

        JPanel enterPanel = new JPanel();
        enterPanel.setBorder(BorderFactory.createTitledBorder("EnterPanel"));
        enterPanel.setLayout(new GridLayout(12,1));

        JPanel resultPanel = new JPanel();
        resultPanel.setBorder(BorderFactory.createTitledBorder("ResultPanel"));
        resultPanel.setLayout(new GridLayout(6,1));

        JLabel adressLabel = new JLabel("Enter Adress File");

        JTextField adress = new JTextField(30);

        JButton enterButton = new JButton("EnterText");

        JLabel eneteredLabel = new JLabel("Entered Text");

        JTextArea textAreaEnteredText = new JTextArea(8, 10);

        JScrollPane pane1 = new JScrollPane(textAreaEnteredText);

        JLabel numbernumberWord = new JLabel("Enter Number Word");

        JTextField numberWord = new JTextField(5);

        JLabel WordLabel = new JLabel("Enter Word");

        JTextField WordField = new JTextField(5);

        JButton ReplaceButton = new JButton("ReplaceWord");

        JButton deleteButton = new JButton("DeleteWord");

        JLabel ResultLabel = new JLabel("Result");

        JTextArea textArea = new JTextArea(5, 10);

        JScrollPane pane2 = new JScrollPane(textArea);

        pane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        enterPanel.add(adressLabel);
        enterPanel.add(adress);
        enterPanel.add(enterButton);
        enterPanel.add(numbernumberWord);
        enterPanel.add(numberWord);
        enterPanel.add(WordLabel);
        enterPanel.add(WordField);
        enterPanel.add(ReplaceButton);
        enterPanel.add(deleteButton);

        resultPanel.add(eneteredLabel);
        resultPanel.add(pane1);
        resultPanel.add(ResultLabel);
        resultPanel.add(pane2);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> wordsArray = new ArrayList<String>();
        String[] array;
        ActionListener alHours = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)  {

                if(e.getSource() == enterButton)
                {
                    try {
                        FileReader fileReader = new FileReader(adress.getText());
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line = "";
                        while ((line = bufferedReader.readLine()) != null) {
                            list.add(line);
                        }

                        for(int i = 0; i < list.size(); i++)
                        {
                            textAreaEnteredText.append(list.get(i) + "\n");
                        }

                        String[] array = new String[list.size()];
                        for (int i = 0; i < list.size(); i++)
                        {
                            array[i] =  list.get(i);
                        }


                        for (int i = 0; i < list.size(); i++)
                        {
                            String[] words = array[i].split("\\s");
                            for (int j = 0; j < words.length; j++)
                            {
                                wordsArray.add(words[j]);
                            }
                        }

                    } catch (IOException event) {
                        event.printStackTrace();
                    }
                }


                if(e.getSource() == ReplaceButton)
                {
                    wordsArray.set(Integer.parseInt(numberWord.getText()) - 1, WordField.getText());
                    for(int i = 0; i < wordsArray.size(); i++)
                    {
                        textArea.append(wordsArray.get(i) + " ");
                    }
                    textArea.append("\n");
                }

                if(e.getSource() == deleteButton)
                {
                    wordsArray.set(Integer.parseInt(numberWord.getText()) - 1, "");
                    for(int i = 0; i < wordsArray.size(); i++)
                    {
                        textArea.append(wordsArray.get(i) + " ");
                    }
                    textArea.append("\n");
                }

            }

        };

        enterButton.addActionListener(alHours);
        ReplaceButton.addActionListener(alHours);
        deleteButton.addActionListener(alHours);

        f.add(enterPanel, BorderLayout.WEST);
        f.add(resultPanel, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);

    }
}