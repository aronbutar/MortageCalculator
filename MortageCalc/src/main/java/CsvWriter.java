import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    private FileWriter fileWriter;

    public CsvWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void writeHeader() throws IOException {
        fileWriter.append("Month");
        fileWriter.append(",");
        fileWriter.append("Mortage");
        fileWriter.append(",");
        fileWriter.append("Balance");
        fileWriter.append(",");
        fileWriter.append("InterestRate");
        fileWriter.append(",");
        fileWriter.append("PaidAmount");
        fileWriter.append("\n");
    }

    public void writeRecord(int month, double mortage,double balance , double interest, double paidAmount) throws IOException {
        fileWriter.append(String.valueOf(month));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(mortage));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(balance));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(interest));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(paidAmount));

    }
    public void closeFile() throws IOException {
        fileWriter.close();
    }
}
