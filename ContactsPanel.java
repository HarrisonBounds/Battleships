package lab7out;

import java.awt.*;
import javax.swing.*;

public class ContactsPanel extends JPanel
{
  // Constructor for the contacts panel.
  public ContactsPanel()
  {
    // Create a list of example contacts.
    DefaultListModel<String> list = new DefaultListModel<String>();
    list.addElement("Person One");
    list.addElement("<html><b>Person Two</b></html>");
    list.addElement("Person Three");
    list.addElement("Person Four");
    list.addElement("<html><b>Person Five</b></html>");
    list.addElement("Person Six");
    list.addElement("<html><b>Person Seven</b></html>");
    list.addElement("<html><b>Person Eight</b></html>");
    list.addElement("Person Nine");

    // Use BorderLayout to lay out the components in this panel.
    this.setLayout(new BorderLayout());

    // Create the contacts label in the north.
    JLabel label = new JLabel("Contacts", JLabel.CENTER);
    this.add(label, BorderLayout.NORTH);    

    // Create the contacts list in the center.
    JList<String> contactList = new JList<String>(list);
    contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    contactList.setLayoutOrientation(JList.VERTICAL);
    contactList.setVisibleRowCount(-1);
    contactList.setPreferredSize(new Dimension(300, 200));
    contactList.setFont(contactList.getFont().deriveFont(Font.PLAIN));
    JPanel contactListBuffer = new JPanel();
    contactListBuffer.add(contactList);
    this.add(contactListBuffer, BorderLayout.CENTER);

    // Create the buttons in the south.
    JPanel buttonsPanel = new JPanel(new BorderLayout());
    JPanel contactButtons = new JPanel();
    JButton deleteButton = new JButton("Delete Contact");
    JButton addButton = new JButton("Add Contact");
    contactButtons.add(deleteButton);
    contactButtons.add(addButton);
    buttonsPanel.add(contactButtons, BorderLayout.NORTH);
    JButton logoutButton = new JButton("Log Out");
    JPanel logoutButtonBuffer = new JPanel();
    logoutButtonBuffer.add(logoutButton);
    buttonsPanel.add(logoutButtonBuffer, BorderLayout.SOUTH);
    this.add(buttonsPanel, BorderLayout.SOUTH);
  }
}
