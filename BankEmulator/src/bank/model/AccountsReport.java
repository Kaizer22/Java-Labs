package bank.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class AccountsReport {
    private static final String REPORT_FOLDER = "reports";

    private static final String REPORT_SEPARATOR = "--------------------------------------------------------";

    private static final String REPORT_TITLE = "Отчет по счетам клиента от %s";
    private static final String CLIENT_NAME = "Клиент: %s %s ";
    private static final String ACCOUNTS_SECTION_TITLE = "Состояние счетов  ";
    private static final String ACCOUNT_ROW_TEMPLATE = "ID: %s, Баланс: %.2f, Дата открытия: %s  ";
    private static final String REPORT_SUMMARY_TITLE = "Расчетная сумма по всем счетам: %.2f  ";

    private static final String DATE_PATTERN = "yyyy-mm-dd HH:MM:ss";


    private Date formDate;
    private double totalBalance;
    private String reportPath;

    public AccountsReport() {
        totalBalance = 0;
    }

    public void formReport(Client client) {
        List<String> report = new LinkedList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        report.add(String.format(REPORT_TITLE, simpleDateFormat.format(new Date())));
        report.add(REPORT_SEPARATOR);
        report.add(String.format(CLIENT_NAME, client.getFirstName(),
                client.getSecondName()));
        report.add(REPORT_SEPARATOR);
        report.add(ACCOUNTS_SECTION_TITLE);
        for (Account account: client.getAccountList()) {
            totalBalance += account.getBalance();
            report.add(String.format(ACCOUNT_ROW_TEMPLATE, account.getAccountId().toString(),
                    account.getBalance(),
                    simpleDateFormat.format(account.getCreationTime())));
        }
        report.add(REPORT_SEPARATOR);
        report.add(String.format(REPORT_SUMMARY_TITLE, totalBalance));
        saveReport(report);
    }

    private void saveReport(List<String> report) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(0));
            PDFont cyrillicFont = PDType0Font.load(document, new File ("src" + File.separator +
                    "fonts" + File.separator + "20211.ttf"));
            contentStream.beginText();
            contentStream.setFont(cyrillicFont, 10);
            contentStream.newLineAtOffset(25, 700);
            contentStream.setLeading(14.5f);

            for (String line: report) {
                contentStream.showText(line);
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.close();



            Date date = new Date();
            reportPath = "src" + File.separator + REPORT_FOLDER + File.separator +
                    "report" + date.getTime() + ".pdf";
            document.save(new File(reportPath));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getReportPath() {
        return reportPath;
    }
}
