package view;

import controller.UserController;
import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

public class UserView {
    public static void ui () {

        System.out.println("data");
        Table table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER );
        table.addCell("Id");
        table.addCell("name");
        table.addCell("gender");
        table.addCell("email");
        UserController.getAllUser().forEach(
                e -> {
                    table.addCell(e.getId().toString());
                    table.addCell(e.getName());
                    table.addCell(e.getGender());
                    table.addCell(e.getEmail());
                }
        );
        System.out.println(table.render());

    }
}
