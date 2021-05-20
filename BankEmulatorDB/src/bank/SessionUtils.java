package bank;

public class SessionUtils {
    public static final String ACCOUNT_TO_DEPOSIT_KEY = "accountToDeposit";

    public static final String ACCOUNT_TRANSFER_FROM_KEY = "accountToTransferFrom";
    public static final String ACCOUNT_WITHDRAW_FROM_KEY = "accountWithdrawFrom";


    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
