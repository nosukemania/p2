import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.File;
import java.net.URL;

public class CharacterCounter extends JFrame {
    private JTextArea textArea;
    private JLabel totalCountLabel;
    private JLabel noSpaceCountLabel;
    private JLabel noSpaceNewlineCountLabel;

    public CharacterCounter() {
        // macOSのメニューバー設定
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("apple.awt.application.name", "counter");

        // アイコン設定
        try {
            // アイコンファイルのパスを設定
            String iconPath = "/counter.icns";
            URL iconUrl = getClass().getResource(iconPath);
            if (iconUrl != null) {
                ImageIcon icon = new ImageIcon(iconUrl);
                setIconImage(icon.getImage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout(20, 20));

        // メニューバーの作成
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("ファイル");
        JMenuItem newItem = new JMenuItem("新規");
        JMenuItem exitItem = new JMenuItem("終了");

        newItem.addActionListener(e -> {
            textArea.setText("");
            updateCounts();
        });

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // メインパネルの作成（余白用）
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // テキストエリアの作成
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setBorder(new CompoundBorder(
            new LineBorder(Color.GRAY, 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // カウントパネルの作成
        JPanel countPanel = new JPanel();
        countPanel.setLayout(new GridLayout(3, 1, 0, 10));
        
        // ラベルのスタイル設定
        Font labelFont = new Font("SansSerif", Font.PLAIN, 14);
        Color labelBgColor = new Color(240, 240, 240);
        
        totalCountLabel = createStyledLabel("総文字数: 0", labelFont, labelBgColor);
        noSpaceCountLabel = createStyledLabel("空白抜き文字数: 0", labelFont, labelBgColor);
        noSpaceNewlineCountLabel = createStyledLabel("空白・改行抜き文字数: 0", labelFont, labelBgColor);
        
        countPanel.add(totalCountLabel);
        countPanel.add(noSpaceCountLabel);
        countPanel.add(noSpaceNewlineCountLabel);
        
        mainPanel.add(countPanel, BorderLayout.SOUTH);

        // テキスト変更リスナーの追加
        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateCounts();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateCounts();
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateCounts();
            }
        });
    }

    private JLabel createStyledLabel(String text, Font font, Color bgColor) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setOpaque(true);
        label.setBackground(bgColor);
        label.setBorder(new CompoundBorder(
            new LineBorder(Color.GRAY, 1),
            new EmptyBorder(10, 15, 10, 15)
        ));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private void updateCounts() {
        String text = textArea.getText();
        
        // 総文字数
        int totalCount = text.length();
        
        // 空白抜き文字数
        int noSpaceCount = text.replace(" ", "").length();
        
        // 空白・改行抜き文字数
        int noSpaceNewlineCount = text.replaceAll("\\s", "").length();
        
        totalCountLabel.setText("総文字数: " + totalCount);
        noSpaceCountLabel.setText("空白抜き文字数: " + noSpaceCount);
        noSpaceNewlineCountLabel.setText("空白・改行抜き文字数: " + noSpaceNewlineCount);
    }

    public static void main(String[] args) {
        // macOSのルックアンドフィールを設定
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            CharacterCounter app = new CharacterCounter();
            app.setVisible(true);
        });
    }
} 