package project;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileWrapperDeluxe {

    static boolean c = true;

    public static void main(final String[] args) {
        // สร้าง frame กำหนดขนาด set default 
        final JFrame f = new JFrame("File Browser");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1500, 1000);
        // สร้าง tree จาก Directory ของไฟล์จาก folder Data ที่ใช้เก็บ ภาพ 100 GB
        final File file = new File("image100gb");
        final MyFile mf = new MyFile(file);
        final TreeModel model = new FileTreeModel(mf);
        final JTree tree = new JTree(model);
        // ลงทะเบียน TreeListener เพื่อเวลาคลิ้กจะได้ชื่อและ path ของไฟล์สำหรับไปแสดงรูป
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                //pathtostrforaddress
                TreePath treePath = e.getPath();
                String print = treePath.toString().replace("[", "").replace("]", "").replace(" ", "");
                String[] j = print.split(",");
                String path = "";
                for (int i = 0; i < j.length; i++) {
                    path += j[i];
                    if (i != j.length - 1) {
                        path += "/";
                    }
                }
                //checkjpg
                String k = j[j.length - 1];
                if (k.charAt(k.length() - 1) == 'g' && k.charAt(k.length() - 2) == 'p' && k.charAt(k.length() - 3) == 'j') {
                    Showpic.pic = path;
                    if (c) {
                        Showpic.draw.start();
                        c = false;
                    }
                }
            }
        });
     
        tree.setShowsRootHandles(true);
       
        tree.setEditable(false);

        final Showpic pi = new Showpic();

        JScrollPane sc = new JScrollPane(tree);

        sc.setBounds(0, 0, 400, 1000);
        f.add(sc);
        f.add(pi);
        f.setVisible(true);
    }
}
