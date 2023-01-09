import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

//import org.eclipse.swt.SWT;
//import org.eclipse.swt.custom.CLabel;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.wb.swt.SWTResourceManager;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Range;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

	/**
	 * Create the frame.
	 */
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1285, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
			contentPane.setLayout(new MigLayout("", "[256px][176px][][][][][128px][128px][128px][128px][128px][][][31.00][][][][][]", "[90px][128px][][256px][][][][][][]"));
			
			JLabel ISELbl = new JLabel();
			ISELbl.setHorizontalAlignment(SwingConstants.CENTER);
			ISELbl.setFont(new Font("Calibri", ISELbl.getFont().getStyle() | Font.BOLD, ISELbl.getFont().getSize() + 2));
			//ISELbl.setBounds(63, 30, 171, 24);
			ISELbl.setText("Image Search Engine");
			contentPane.add(ISELbl, "cell 0 0,grow");
			
			JLabel resultsLbl = new JLabel();
			resultsLbl.setHorizontalAlignment(SwingConstants.CENTER);
			resultsLbl.setFont(new Font("Calibri", resultsLbl.getFont().getStyle() | Font.BOLD, resultsLbl.getFont().getSize() + 2));
			//resultsLbl.setBounds(542, 30, 60, 24);
			resultsLbl.setText("Results");
			contentPane.add(resultsLbl, "cell 8 0,grow");
			
			JButton btnBrowse = new JButton();
			//btnBrowse.setBounds(107, 84, 82, 32);
			btnBrowse.setText("Browse...");
			contentPane.add(btnBrowse, "cell 0 1,alignx center");
			
			
			//String file = "C:\\Users\\acreg\\Desktop\\final year\\Project\\images\\Crowds\\Crowds001.jpg";
			//
			//ImageIcon pic = new ImageIcon(file.toString());
			////lblNewLabel_1.setIcon(pic);
								
				
			Border loweredbevel = BorderFactory.createLoweredBevelBorder();
			
			JLabel lblResImg_1 = new JLabel("Image 1");
			lblResImg_1.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(lblResImg_1, "cell 6 1,grow");
			lblResImg_1.setBorder(loweredbevel);
			
			JLabel lblResImg_2 = new JLabel("Image 2");
			lblResImg_2.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(lblResImg_2, "cell 7 1,grow");
			lblResImg_2.setBorder(loweredbevel);
			
			JLabel lblResImg_3 = new JLabel("Image 3");
			lblResImg_3.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(lblResImg_3, "cell 8 1,grow");
			lblResImg_3.setBorder(loweredbevel);
			
			JLabel lblResImg_4 = new JLabel("Image 4");
			lblResImg_4.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(lblResImg_4, "cell 9 1,grow");
			lblResImg_4.setBorder(loweredbevel);
			
			JLabel lblResImg_5 = new JLabel("Image 5");
			lblResImg_5.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(lblResImg_5, "cell 10 1,grow");
			lblResImg_5.setBorder(loweredbevel);
			
			JLabel lblSearchImg = new JLabel("New label");
			lblSearchImg.setHorizontalAlignment(SwingConstants.CENTER);
			//lblNewLabel_1.setBounds(27, 142, 256, 256);
			lblSearchImg.setText("waiting...");
			contentPane.add(lblSearchImg, "cell 0 2 1 2,grow");
			lblSearchImg.setBorder(loweredbevel);
			
			JLabel lblResImg_6 = new JLabel("Image 6");
			lblResImg_6.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(lblResImg_6, "cell 7 5,grow");
			lblSearchImg.setBorder(loweredbevel);
			
			
			JButton btnSearch = new JButton();
			//btnSearch.setBounds(128, 422, 61, 32);
			btnSearch.setText("Search");
			contentPane.add(btnSearch, "cell 0 8,alignx center,growy");
			
			JButton btnClear = new JButton();
			//btnClear.setBounds(531, 422, 87, 32);
			btnClear.setText("Clear data");
			contentPane.add(btnClear, "cell 8 8,grow");
			
			
			
			
			btnClear.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					lblSearchImg.setText("waiting...");
					lblSearchImg.setIcon(null);
					
					lblResImg_1.setIcon(null);	
					lblResImg_1.setText("Image 1");
					
					lblResImg_2.setIcon(null);
					lblResImg_2.setText("Image 2");
					
					lblResImg_3.setIcon(null);
					lblResImg_3.setText("Image 3");
					
					lblResImg_4.setIcon(null);
					lblResImg_4.setText("Image 4");
					
					lblResImg_5.setIcon(null);
					lblResImg_5.setText("Image 5");
					
				}
			});
		
		
			btnBrowse.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
				    JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JPG & GIF Images", "jpg", "gif");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(null);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	File file = new File(chooser.getSelectedFile().getAbsolutePath());

				    	String queryImg = file.toString();
						ImageIcon pic = new ImageIcon(queryImg);
						ImageIcon imageIcon = new ImageIcon(pic.getImage().getScaledInstance(256, 256, Image.SCALE_DEFAULT));
				        
						lblSearchImg.setText(queryImg);
						lblSearchImg.setIcon(imageIcon);
						lblSearchImg.setText("");
				         
				         				       			         
				    }					
					
				}
			});
			
			
			btnSearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ArrayList<Image> proImgs = new ArrayList<Image>(images.details(lblSearchImg.getIcon(), lblSearchImg.getText()));
					
					ImageIcon resIcon1 = new ImageIcon(proImgs.get(0).getScaledInstance(128, 128, Image.SCALE_DEFAULT));
					lblResImg_1.setIcon(resIcon1);	
					lblResImg_1.setText("");
					
					ImageIcon resIcon2 = new ImageIcon(proImgs.get(1).getScaledInstance(128, 128, Image.SCALE_DEFAULT));
					lblResImg_2.setIcon(resIcon2);
					lblResImg_2.setText("");
					
					ImageIcon resIcon3 = new ImageIcon(proImgs.get(2).getScaledInstance(128, 128, Image.SCALE_DEFAULT));
					lblResImg_3.setIcon(resIcon3);
					lblResImg_3.setText("");
					
					ImageIcon resIcon4 = new ImageIcon(proImgs.get(3).getScaledInstance(128, 128, Image.SCALE_DEFAULT));
					lblResImg_4.setIcon(resIcon4);
					lblResImg_4.setText("");
					
					ImageIcon resIcon5 = new ImageIcon(proImgs.get(4).getScaledInstance(128, 128, Image.SCALE_DEFAULT));
					lblResImg_5.setIcon(resIcon5);
					lblResImg_5.setText("");
					
				
				}
			});			
	}
}



