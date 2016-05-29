import java.util.ArrayList;

import static java.lang.Math.abs;


public class GetListOfTransaction {

    public ArrayList<Transaction> execute(ArrayList<PersonBalance> overpayments, ArrayList<PersonBalance> underpayments)
    {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
            PersonBalance workingOverpayment;
            while (overpayments.size() > 0 && underpayments.size() > 0)
            {
                workingOverpayment = getBiggestPayment(overpayments);
                PersonBalance coveringPayment = findCoveringUnderpayment(underpayments,workingOverpayment);
                if (coveringPayment!=null){
                    transactions.add(new Transaction(coveringPayment.getName(), workingOverpayment.getName(), abs(coveringPayment.getAmount())));
                    underpayments.remove(coveringPayment);
                    overpayments.remove(workingOverpayment);
                }
                else
                {
                    PersonBalance workingUnderpayment = getBiggestPayment(underpayments);
                    if (isUnderpaymentBigger(workingOverpayment, workingUnderpayment))
                    {
                        transactions.add(new Transaction(workingUnderpayment.getName(),workingOverpayment.getName(),abs(workingOverpayment.getAmount())));
                        overpayments.remove(workingOverpayment);
                        int index = underpayments.indexOf(workingUnderpayment);
                        workingUnderpayment.setAmount(workingUnderpayment.getAmount() + workingOverpayment.getAmount());
                        if (index != -1) {
                            underpayments.set(index, workingUnderpayment);
                        }
                        else break;
                    }
                    else
                    {
                        transactions.add(new Transaction(workingUnderpayment.getName(),workingOverpayment.getName(),abs(workingUnderpayment.getAmount())));
                        underpayments.remove(workingUnderpayment);
                        int index = overpayments.indexOf(workingOverpayment);
                        workingOverpayment.setAmount(workingUnderpayment.getAmount() + workingOverpayment.getAmount());
                        if (index != -1) {
                            overpayments.set(index, workingOverpayment);
                        }
                        else break;
                    }
                }
        }
        return transactions;
    }

    private boolean isUnderpaymentBigger(PersonBalance workingOverpayment, PersonBalance workingUnderpayment) {
        return (workingOverpayment.getAmount() + workingUnderpayment.getAmount()) < 0;
    }

    private PersonBalance findCoveringUnderpayment(ArrayList<PersonBalance> underpayments, PersonBalance workingOverpayment)
    {
        for (PersonBalance payment: underpayments) {
            if (payment.getAmount() + workingOverpayment.getAmount() == 0){
                return payment;
            }
        }
        return null;
    }

    private PersonBalance getBiggestPayment(ArrayList<PersonBalance> payments)
    {
        PersonBalance result = payments.get(0);
        for (PersonBalance payment: payments)
        {
            if (abs(payment.getAmount()) > abs(result.getAmount())){
                result = payment;
            }
        }
        return result;
    }

}
