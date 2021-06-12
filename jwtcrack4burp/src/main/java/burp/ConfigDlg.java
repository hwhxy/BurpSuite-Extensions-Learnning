package burp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 配置窗口类，负责显示配置窗口，处理窗口消息
 */
public class ConfigDlg extends JDialog {
    private final JPanel mainPanel = new JPanel();

    private final JLabel lbfuzzpath = new JLabel("JWTCRACK fuzzpath");
    private final JTextField tffuzzpath = new JTextField(30);
    private final JLabel lbJWTCRACKPath = new JLabel("JWTCRACK path:");
    private final JTextField tfJWTCRACKPath = new JTextField(30);
    private final JButton btnBrowse = new JButton("Browse");
    private final JLabel lbOption = new JLabel("JWTCRACK option:");
    private final JTextField tfOption = new JTextField(30);
    private final JLabel lbPrompt = new JLabel("Prompt:");
    private final JButton btnOK = new JButton("OK");
    private final JButton btnCancel = new JButton("Cancel");


    public ConfigDlg() {
        initGUI();
        initEvent();
        initValue();
        this.setTitle("plug-in4-jwtcrack4burp");
    }


    /**
     * 初始化UI
     */
    private void initGUI() {
        //tip 模块


        mainPanel.setLayout(new GridBagLayout());
        //第一行
        mainPanel.add(lbfuzzpath, new GBC(0, 0, 2, 1).setFill(GBC.BOTH).setInsets(10, 10, 2, 0));
        mainPanel.add(tffuzzpath, new GBC(2, 0, 3, 1).setFill(GBC.BOTH).setInsets(10, 0, 2, 10));
        mainPanel.add(btnBrowse, new GBC(5, 0, 1, 1).setFill(GBC.BOTH).setInsets(10, 0, 2, 10));

        JLabel lbfuzzpathHelp = new JLabel("?");
        lbfuzzpathHelp.setToolTipText("相关fuzz的目录");
        mainPanel.add(lbfuzzpathHelp, new GBC(5, 0, 6, 1).setFill(GBC.BOTH).setInsets(10, 0, 2, 10));

        //第二行
        mainPanel.add(lbJWTCRACKPath, new GBC(0, 1, 2, 1).setFill(GBC.BOTH).setInsets(10, 10, 2, 0));
        mainPanel.add(tfJWTCRACKPath, new GBC(2, 1, 3, 1).setFill(GBC.BOTH).setInsets(10, 0, 2, 10));
        mainPanel.add(btnBrowse, new GBC(5, 1, 1, 1).setFill(GBC.BOTH).setInsets(10, 0, 2, 10));

        //第三行
        mainPanel.add(lbOption, new GBC(0, 2, 2, 1).setFill(GBC.BOTH).setInsets(10, 10, 2, 0));
        mainPanel.add(tfOption, new GBC(2, 2, 3, 1).setFill(GBC.BOTH).setInsets(10, 0, 2, 10));

        JLabel lbOptionHelp = new JLabel("?");
        lbOptionHelp.setToolTipText("一些其他选项，后期可以在代码里面加");
        mainPanel.add(lbOptionHelp, new GBC(5, 2, 1, 1).setFill(GBC.BOTH).setInsets(10, 0, 2, 10));

        //第四行
        mainPanel.add(btnOK, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setInsets(10, 10, 10, 0));
        mainPanel.add(btnCancel, new GBC(1, 3, 1, 1).setFill(GBC.BOTH).setInsets(10, 0, 10, 10));

        if (Util.getOSType() == Util.OS_LINUX) {
            lbPrompt.setText("Notice: The command will be copied to the clipboard. Paste it into Terminal!");
            mainPanel.add(lbPrompt, new GBC(2, 3, 1, 1).setFill(GBC.BOTH).setInsets(10, 0, 2, 10));
        } else if (Util.getOSType() == Util.OS_MAC) {
            lbPrompt.setText("Notice: Please ensure that Terminal is in running state!");
            mainPanel.add(lbPrompt, new GBC(2, 3, 1, 1).setFill(GBC.BOTH).setInsets(10, 0, 2, 10));
        }
        lbPrompt.setForeground(new Color(0, 0, 255));

        this.setModal(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.add(mainPanel);
        //使配置窗口自动适应控件大小，防止部分控件无法显示
        this.pack();
        //居中显示配置窗口
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screensize.width / 2 - this.getWidth() / 2, screensize.height / 2 - this.getHeight() / 2, this.getWidth(), this.getHeight());
        BurpExtender.callbacks.customizeUiComponent(this);
    }


    /**
     * 初始化事件
     */
    private void initEvent() {

        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//设置只能选择目录
                int returnVal = chooser.showOpenDialog(ConfigDlg.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String selectPath = chooser.getSelectedFile().getPath();
                    tfJWTCRACKPath.setText(selectPath);
                    chooser.hide();
                }
            }
        });


        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config.setIsInject(true);
                Config.SetFuzzPath(tffuzzpath.getText().trim());
                Config.setJWTCRACKPath(tfJWTCRACKPath.getText().trim());
                Config.setjwt(tfOption.getText().trim());
                ConfigDlg.this.dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config.setIsInject(false);
                ConfigDlg.this.dispose();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Config.setIsInject(false);
            }
        });

    }


    /**
     * 为控件赋值
     */
    public void initValue() {
        tffuzzpath.setText(Config.GetFuzzPath());
        tfJWTCRACKPath.setText(Config.getJWTCRACKPath());
        tfOption.setText(Config.getJWTCRACKOptionsCommand());
    }
}