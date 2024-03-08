package unitl;

import controller.UserController;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

public class UntilTable {
    private final UserController userController;
    public UntilTable(){
        userController = Singleton.userController();
    }
    public static void welcomeTable (){
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE , ShownBorders.SURROUND);
        table.addCell("1. Display All User");
        table.addCell("2. Update User");
        table.addCell("3. Delete User");
        table.addCell("4. Insert User");
        table.addCell("5. Exit");
        System.out.println(table.render());
    }
    public  void displayUserTable (){
        Table table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
        table.addCell("ID");
        table.addCell("UserName");
        table.addCell("UserGender");
        table.addCell("UserEmail");
        userController.getUser().forEach(e -> {
            table.addCell(e.getUserId().toString());
            table.addCell(e.getUserName());
            table.addCell(e.getUserGender());
            table.addCell(e.getUserEmail());
        });
        System.out.println(table.render());
    }
}
