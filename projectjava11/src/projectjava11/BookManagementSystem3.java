/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectjava11; 
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
class Book {
    String title, author;
    int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}

public class BookManagementSystem3 {
    private static final String FILE_NAME = "books.txt"; 
    private static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        preloadBooks(); 
        loadBooksFromFile();

        showWelcomeWindow(); 
    }

   private static void showWelcomeWindow() {
    JFrame welcomeFrame = new JFrame("Welcome");
    welcomeFrame.setSize(500, 400);
    welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    welcomeFrame.setLayout(new BorderLayout());

    JLabel imageLabel = new JLabel();
   ImageIcon originalIcon = new ImageIcon("/Users/Layan/NetBeansProjects/projectjava11/javaproj/w1.jpg");

    int imageWidth = 400; 
    int imageHeight = 300; 

    Image resizedImage = originalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
    imageLabel.setIcon(new ImageIcon(resizedImage));

    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageLabel.setVerticalAlignment(SwingConstants.CENTER); 


    welcomeFrame.add(imageLabel, BorderLayout.CENTER); 

 
  JButton enterButton = new JButton("Enter");
enterButton.setFont(new Font("Arial", Font.BOLD, 16));
enterButton.setBackground(new Color(33, 150, 243)); 
enterButton.setForeground(Color.WHITE); 
enterButton.setOpaque(true); 
enterButton.setBorderPainted(false); 

    enterButton.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            welcomeFrame.dispose();
            showMainFrame(); 
        }
    });
    welcomeFrame.add(enterButton, BorderLayout.SOUTH); 

    welcomeFrame.setLocationRelativeTo(null); 
    welcomeFrame.setVisible(true);
}

    private static void showMainFrame() {
        JFrame mainFrame = new JFrame("Library Management System");
        mainFrame.setSize(500, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Option");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        JMenuItem deleteBookItem = new JMenuItem("Delete Book");
        JMenuItem viewBooksItem = new JMenuItem("View Books");

        menu.add(addBookItem);
        menu.add(deleteBookItem);
        menu.add(viewBooksItem);
        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);

        JLabel imageLabel = new JLabel();
          ImageIcon originalIcon = new ImageIcon("/Users/Layan/NetBeansProjects/projectjava11/javaproj/w2.jpg"); 
        Image resizedImage = originalIcon.getImage().getScaledInstance(
                mainFrame.getWidth(), mainFrame.getHeight(), Image.SCALE_SMOOTH); 
        imageLabel.setIcon(new ImageIcon(resizedImage));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainFrame.add(imageLabel, BorderLayout.CENTER); 
        mainFrame.setLocationRelativeTo(null); 

        addBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                openAddBookWindow();
            }
        });
        deleteBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {  
                
            openDeleteBookWindow();
            }
        });
        viewBooksItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                openViewBooksWindow();
            }
        });

        mainFrame.setVisible(true);
    }

    private static void openAddBookWindow() {
        JFrame addFrame = new JFrame("Add Book");
        addFrame.setSize(400, 300);
        addFrame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel titleLabel = new JLabel("Book Title:");
        JTextField titleField = new JTextField();

        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField();

        JLabel yearLabel = new JLabel("Year:");
        JTextField yearField = new JTextField();

      JButton saveButton = new JButton("Save");
saveButton.setBackground(new Color(33, 150, 243)); 
saveButton.setForeground(Color.WHITE); 
saveButton.setOpaque(true); 
saveButton.setBorderPainted(false); 


        addFrame.add(titleLabel);
        addFrame.add(titleField);
        addFrame.add(authorLabel);
        addFrame.add(authorField);
        addFrame.add(yearLabel);
        addFrame.add(yearField);
        addFrame.add(new JLabel());
        addFrame.add(saveButton);

 saveButton.addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        try {
            if (titleField.getText().trim().isEmpty() || authorField.getText().trim().isEmpty() || yearField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Fields cannot be empty!");
            }

            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            int year = Integer.parseInt(yearField.getText().trim());

            books.add(new Book(title, author, year));
            saveBookToFile(new Book(title, author, year));

            JOptionPane.showMessageDialog(addFrame, "Book Added!");
            addFrame.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(addFrame, "Please Enter a Valid Year!");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(addFrame, "field cannot be empty!");
        }
    }
});
        addFrame.setVisible(true);
    }

    private static void openDeleteBookWindow() {
        JFrame deleteFrame = new JFrame("Delete Book");
        deleteFrame.setSize(400, 200);
        deleteFrame.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Book Title:");
        JTextField titleField = new JTextField(20);

        JButton deleteButton = new JButton("Delete");
deleteButton.setBackground(Color.RED); 
deleteButton.setForeground(Color.WHITE); 
deleteButton.setOpaque(true); 
deleteButton.setBorderPainted(false); 


        deleteFrame.add(titleLabel);
        deleteFrame.add(titleField);
        deleteFrame.add(deleteButton);

        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String title = titleField.getText();
              for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).title.equals(title)) {
                       books.remove(i);
                         break; 
                        }
                }
                saveAllBooksToFile(); 
                JOptionPane.showMessageDialog(deleteFrame, "Book Deleted (if it existed)!");
                deleteFrame.dispose();
            }
        });

        deleteFrame.setVisible(true);
    }

    private static void openViewBooksWindow() {
        JFrame viewFrame = new JFrame("View Books");
        viewFrame.setSize(500, 300);

        String[] columns = {"Title", "Author", "Year"};
        String[][] data = new String[books.size()][3];
        for (int i = 0; i < books.size(); i++) {
            data[i][0] = books.get(i).title;
            data[i][1] = books.get(i).author;
            data[i][2] = String.valueOf(books.get(i).year);
        }

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        viewFrame.add(scrollPane);

        viewFrame.setVisible(true);
    }

    private static void preloadBooks() {
       if (!new File(FILE_NAME).exists()) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("Rich Dad poor Dad,Robert,1997\n");
            writer.write("The power of Now,Eckhart,2016\n");
            writer.write("Think and Grow,Napoleon,1937\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    private static void saveBookToFile(Book book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(book.title + "," + book.author + "," + book.year);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveAllBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                writer.write(book.title + "," + book.author + "," + book.year);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0];
                String author = parts[1];
                int year = Integer.parseInt(parts[2]);
                books.add(new Book(title, author, year));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing file found. Starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
