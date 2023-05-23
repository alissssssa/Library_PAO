package Library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {
    private static final String AUDIT_FILE_PATH = "//Users//alisadraghici//IdeaProjects//Library//src//data//AuditActions";

    private String actionName;
    private LocalDateTime timestamp;

    public Audit(String actionName) {
        this.actionName = actionName;
        this.timestamp = LocalDateTime.now();
    }

    public void saveAuditAction() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AUDIT_FILE_PATH, true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTimestamp = timestamp.format(formatter);
            writer.write(actionName + "," + formattedTimestamp);
            writer.newLine();
        } catch (Exception e){
            System.err.println(e.toString());
        }
    }
}
