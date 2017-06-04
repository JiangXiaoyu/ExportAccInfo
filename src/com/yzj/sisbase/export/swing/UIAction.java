package com.yzj.sisbase.export.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.yzj.sisbase.export.accinfo.DeleteAccinfo;
import com.yzj.sisbase.export.accinfo.ExportAccinfo;
import com.yzj.sisbase.export.accinfo.ImportAccinfo;
import com.yzj.sisbase.export.repository.ConnectionPool;
import com.yzj.sisbase.export.repository.ExcelUtility;

public class UIAction extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1622794139980871560L;
	public static String feedBack = "";
	
	public static JPanel accinfoTool;
	public static JLabel inputAcc;
	public static JLabel inputFile;
	public static JLabel deleteAcc;
	public static JTextField inputAccText;
	public static JTextField inputFileText;
	public static JTextField deleteAccText;
	public static JButton exportButton;
	public static JButton importButton;
	public static JButton deleteButton;
	public static JButton chooseFile;
	public static JButton resetButton;
	public static JTextArea exceptionReport;
	public static JScrollPane scrollBar;

	public static void main(String[] args) {
		try {
//			Connection conn = ConnectionPool.getConnection();  DB链接
			Execute();
		} catch (Exception e) {
			System.out.println("数据库配置失败：" + e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public static void Execute(){
		UIAction inst = new UIAction(null);
		inst.setResizable(false);
		inst.setLocationRelativeTo(null);
		inst.setVisible(true);
		
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportPerformed();
			}
		});
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importPerformed();
			}
		});
		chooseFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				chooseFilePerformed();
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletePerformed();
			}
		});
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				resetButtonPerformed();
			}
		});
	}

	public static void exportPerformed() {
		ExportAccinfo export = new ExportAccinfo();
		feedBack += export.exportAccinfo(inputAccText.getText()) + "\n";
		Font font = new Font(null,0,20);
		exceptionReport.setFont(font);
		exceptionReport.setForeground(Color.RED);
		exceptionReport.setText(feedBack);
		feedBack += "\n";
	}
	
	public static void chooseFilePerformed(){
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//		String rootPath = System.getProperty("user.dir") + "/AccInfo/accInfo.xls";
		String rootPath = System.getProperty("user.dir");
		jfc.setSelectedFile(new File(rootPath));
		jfc.showDialog(new JLabel(), "选择");
		File file = jfc.getSelectedFile();
		if (file.isFile()) {
			inputFileText.setText(file.getAbsolutePath());
		}
	}
   
	public static void importPerformed() {		
		String filePath = inputFileText.getText();
		String error = ExcelUtility.isExcelFile(filePath);
		if(error.isEmpty()){
			exceptionReport.setText("");
			ImportAccinfo importAcc = new ImportAccinfo();
			feedBack += importAcc.importAccinfo(inputFileText.getText()) + "\n";
	    } else {
	    	feedBack += error;
	    }
		Font font = new Font(null,0,20);
		exceptionReport.setFont(font);
		exceptionReport.setForeground(Color.RED);
		exceptionReport.setText(feedBack);
		feedBack += "\n";
	}
	
	public static void deletePerformed(){
		DeleteAccinfo delete = new DeleteAccinfo();
		feedBack += delete.deleteAccinfo(deleteAccText.getText()) + "\n";;
		Font font = new Font(null,0,20);
		exceptionReport.setFont(font);
		exceptionReport.setForeground(Color.RED);
		exceptionReport.setText(feedBack);
		feedBack += "\n";
	}
	
	public static void resetButtonPerformed(){
		feedBack = "";
		inputAccText.setText("");
		inputFileText.setText("");
		deleteAccText.setText("");
		exceptionReport.setText("");
	}

	public UIAction(JFrame frame) {
		super(frame);
		initGUI();
	}

	private void initGUI() {
		try {
			setTitle("账户导入导出工具");
			getContentPane().setLayout(null);
			{
				accinfoTool = new JPanel();
				getContentPane().add(accinfoTool);
				accinfoTool.setBounds(41, 34, 713, 500);
				accinfoTool.setBorder(BorderFactory.createTitledBorder("账户导入导出:"));
				accinfoTool.setLayout(null);
				{
					inputAcc = new JLabel();
					accinfoTool.add(inputAcc);
					inputAcc.setText("请输入账号:");
					inputAcc.setBounds(25, 56, 90, 15);
				}
				{
					inputFile = new JLabel();
					accinfoTool.add(inputFile);
					inputFile.setText("请浏览文件:");
					inputFile.setBounds(25, 126, 90, 15);
				}
				{
					inputAccText = new JTextField();
					accinfoTool.add(inputAccText);
					inputAccText.setBounds(119, 52, 133, 22);
				}
				{
					exportButton = new JButton("导出账户");
					accinfoTool.add(exportButton);
					exportButton.setBounds(119, 86, 133, 22);
				}
				{
					inputFileText = new JTextField();
					accinfoTool.add(inputFileText);
					inputFileText.setBounds(119, 122, 133, 22);
				}				
				{
					chooseFile = new JButton("选择文件");
					accinfoTool.add(chooseFile);
					chooseFile.setBounds(119, 154, 133, 22);
				}				
				{
					importButton = new JButton("导入账户");
					accinfoTool.add(importButton);
					importButton.setBounds(119, 186, 133, 22);
				}
				{
					deleteAcc = new JLabel();
					accinfoTool.add(deleteAcc);
					deleteAcc.setText("请输入账号:");
					deleteAcc.setBounds(25, 218, 90, 15);
				}
				{
					deleteAccText = new JTextField();
					accinfoTool.add(deleteAccText);
					deleteAccText.setBounds(119, 218, 133, 22);
				}
				{
					deleteButton = new JButton("删除账户");
					accinfoTool.add(deleteButton);
					deleteButton.setBounds(119, 250, 133, 22);
				}
				{
					resetButton = new JButton("重置");
					accinfoTool.add(resetButton);
					resetButton.setBounds(119, 300, 133, 22);
				}
				{
					exceptionReport = new JTextArea();
					exceptionReport.setWrapStyleWord(true);
					exceptionReport.setLineWrap(true);					
					scrollBar = new JScrollPane(exceptionReport);
					scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
					accinfoTool.add(scrollBar);
					scrollBar.setBounds(280, 34, 400, 430);
				}
			}
			setSize(800, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}