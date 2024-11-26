
package applications;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;


public class Server1 implements ActionListener
{
    JTextField t1;
    JPanel a1;
    static Box vertical=Box.createVerticalBox();
    static JFrame f= new JFrame(); 
    static DataOutputStream dout;
    Server1()
    {
        f.setLayout(null);
        JPanel p1=new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2=i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel back=new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent aobj)
        {
            System.exit(0);
        }
    });
        
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/1.jpg"));
        Image i5=i4.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i6= new ImageIcon(i5);
        JLabel profile=new JLabel(i6);
        profile.setBounds(40,10,50,50);
        p1.add(profile);
        

        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8=i7.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i9= new ImageIcon(i8);
        JLabel video=new JLabel(i9);
        video.setBounds(300,20,35,30);
        p1.add(video);

        ImageIcon x1=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image x2=x1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon x3= new ImageIcon(x2);
        JLabel call=new JLabel(x3);
        call.setBounds(360,20,35,30);
        p1.add(call);
        
        
        ImageIcon x4=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image x5=x4.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon x6= new ImageIcon(x5);
        JLabel arrow=new JLabel(x6);
        arrow.setBounds(420,20,5,25);
        p1.add(arrow);
        
        JLabel name=new JLabel("Vaishh");
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        name.setBounds(110,10,100,20);
        p1.add(name);
        
        JLabel status=new JLabel("online");
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        status.setBounds(110,35,100,20);
        p1.add(status);
        
        
        a1=new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);
        
        t1=new JTextField();
        t1.setBounds(5,595,310,40);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(t1);
        
        JButton b1=new JButton("Send");
        b1.setBounds(320,595,110,40);
        b1.setBackground(new Color(7,94,84));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        f.add(b1);
        
        f.setSize(450,650);
        f.setLocation(100,10);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        
        
        f.setVisible(true);
        
    }
    public void actionPerformed(ActionEvent eobj) 
    {
       try
       {
        String out=t1.getText();
        
        
        JPanel p2=formatLabel(out);
        
        a1.setLayout(new BorderLayout());
        JPanel right=new JPanel(new BorderLayout());
        right.add(p2,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        
        a1.add(vertical,BorderLayout.PAGE_START);
        dout.writeUTF(out);
        //repaint();
        t1.setText("");
       f.validate();
       }catch(Exception e)
       {
           
       }
    }
    
    public static JPanel formatLabel(String out)
    { 
        JPanel panel= new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel output=new JLabel("<html><p style=\"width: 150px\">"+ out +"</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        
        output.setBackground(Color.orange);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);
        
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:MM");
        JLabel time= new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
        
    }
    public static void main(String[] arg)
    {
        new Server1();
        try
        {
         ServerSocket ssobj=new ServerSocket(2100);  
         while(true)
         {
             Socket s=ssobj.accept();
             DataInputStream din=new DataInputStream(s.getInputStream());
             dout=new DataOutputStream(s.getOutputStream());
             
             while(true)
             {
                 String msg=din.readUTF();
             
                 JPanel panel=formatLabel(msg);
                 JPanel left=new JPanel(new BorderLayout());
                 left.add(panel,BorderLayout.LINE_START);
                 vertical.add(left);
                 f.validate();
                 
                 
             }
             
         }
        }catch(Exception eobj)
        {}
    }
    
}
