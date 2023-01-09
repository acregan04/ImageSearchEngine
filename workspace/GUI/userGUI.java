import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.custom.CLabel;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class userGUI {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			userGUI window = new userGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
			
		shell = new Shell();
		shell.setSize(935, 667);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Label ISELbl = new Label(shell, SWT.NONE);
		ISELbl.setBounds(63, 30, 171, 24);
		ISELbl.setText("Image Search Engine");
		ISELbl.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.BOLD));
		
		Label resultsLbl = new Label(shell, SWT.NONE);
		resultsLbl.setBounds(542, 30, 60, 24);
		resultsLbl.setFont(SWTResourceManager.getFont("Calibri", 12, SWT.BOLD));
		resultsLbl.setText("Results");
		
		Button btnBrowse = new Button(shell, SWT.NONE);
		btnBrowse.setBounds(107, 84, 82, 32);

		btnBrowse.setText("Browse...");
		btnBrowse.setFont(SWTResourceManager.getFont("Calibri", 11, SWT.NORMAL));
		
		CLabel lblSearchImg = new CLabel(shell, SWT.BORDER | SWT.CENTER);
		lblSearchImg.setBounds(27, 142, 256, 256);
		lblSearchImg.setFont(SWTResourceManager.getFont("Calibri", 11, SWT.NORMAL));
		lblSearchImg.setText("waiting...");
		
		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.setBounds(128, 422, 61, 32);
		btnSearch.setFont(SWTResourceManager.getFont("Calibri", 11, SWT.NORMAL));
		btnSearch.setText("Search");
		
		Button btnClear = new Button(shell, SWT.NONE);
		btnClear.setBounds(531, 422, 87, 32);
		btnClear.setText("Clear data");
		btnClear.setFont(SWTResourceManager.getFont("Calibri", 11, SWT.NORMAL));
		
		DragSource dragSource = new DragSource(shell, DND.DROP_MOVE);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(429, 157, 70, 20);
		lblNewLabel.setText("New Label");
		
		
		
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
							
			    JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG & GIF Images", "jpg", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	File file = new File(chooser.getSelectedFile().getAbsolutePath());
					//ImageIcon pic = new ImageIcon(file.toString());
					//lblSearchImg.setIconImage(pic);
					
					ImageIcon pic = new ImageIcon(file.toString());
					lblNewLabel.setIcon(pic);
					
					
					
			    }
			}
		});

	}
}
