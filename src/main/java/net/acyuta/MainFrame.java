package net.acyuta;

import net.acyuta.vk.VkLogic;
import net.acyuta.vk.api.messages.utils.ChatDialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by acyuta on 18.01.15.
 */
public class MainFrame extends JFrame {

    JList<String> list;
    JTextArea textArea;
    JTextField textField;
    VkLogic logic;
    JButton showMessagesButton;
    JButton sendMessageButton;

    String currentDialogID;

    public MainFrame(VkLogic logic) throws HeadlessException {

        this.logic = logic;
        setTitle("Dream Team Chat");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setupUI();
    }



    private void setupUI() {
        setLayout(new BorderLayout());
        JPanel headPanel = new JPanel(new BorderLayout());

        list = new JList<String>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setModel(new DefaultListModel<String>());
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = ((JList<String>)e.getSource()).getSelectionModel();
                if (lsm.isSelectionEmpty())
                    block();
                else
                    unblock();
            }
        });

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(450,80));
        headPanel.add(listScroller,BorderLayout.PAGE_END);

        /* текстовая область */
        textArea = new JTextArea();
        JScrollPane textScroller = new JScrollPane(textArea);
        textArea.setEditable(false);
        add(textScroller, BorderLayout.CENTER);

        /* панель отправки сообщения */
        JPanel messagePanel = new JPanel(new BorderLayout());
        textField = new JTextField();
        messagePanel.add(textField,BorderLayout.CENTER);
        sendMessageButton = new JButton("SendMessage");
        messagePanel.add(sendMessageButton,BorderLayout.LINE_END);

        add(messagePanel, BorderLayout.PAGE_END);


        /* КНОПКИ */
        JPanel controlButtonsPanel = new JPanel(new FlowLayout());
        JButton loadMultidialogsButton = new JButton("Load List");
        loadMultidialogsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
                Map<Integer,ChatDialog> dialogs = logic.getAllMultidialogs();

                for (Map.Entry<Integer,ChatDialog> d : dialogs.entrySet()) {
                    StringBuilder builder = new StringBuilder();
                    ChatDialog chat = d.getValue();
                    Date time = new Date(Long.valueOf(chat.date)*1000);
                    builder.append(d.getKey())
                            .append(" - ")
                            .append(chat.title)
                            .append(' ')
                            .append(new SimpleDateFormat("H:m d-M-y").format(time))
                            .append(" (")
                            .append(chat.body)
                            .append(')');

                    model.addElement(builder.toString());
                }
            }
        });

        showMessagesButton = new JButton("Show Dialog");
        showMessagesButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = list.getSelectedValue().split("-")[0].trim();

            }
        });
        controlButtonsPanel.add(showMessagesButton);
        controlButtonsPanel.add(loadMultidialogsButton);
        headPanel.add(controlButtonsPanel,BorderLayout.PAGE_START);


        //Устанавливаем панель в главный фрейм
        add(headPanel,BorderLayout.PAGE_START);
        block();

    }

    private void unblock() {
        setDialogButtonsEnabled(true);
    }

    private void block() {
        setDialogButtonsEnabled(false);
    }

    private void setDialogButtonsEnabled(boolean state) {
        sendMessageButton.setEnabled(state);
        showMessagesButton.setEnabled(state);
    }
}
