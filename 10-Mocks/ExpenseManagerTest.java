package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {

    @Test
    public void calculateTotalTest(){
        IFancyDatabase DbMock = mock(FancyDatabase.class);
        when(DbMock.queryAll()).thenReturn(new ArrayList<>());

        List<Expense> expensesList = Arrays.asList(
                new Expense(),
                new Expense(),
                new Expense()
        );
        //for the expected sum to be different than 0
        for(Expense expense: expensesList){
            expense.setAmount(5);
        }

        ExpenseRepository repository = mock(ExpenseRepository.class);
        when(repository.getExpenses()).thenReturn(expensesList);

        assertEquals(3, repository.getExpenses().size());


        FancyService service = mock(FancyService.class);
        ExpenseManager manager = new ExpenseManager(service, repository);
        var result = manager.calculateTotal();

        assertEquals(15, result);
    }

    @Test
    public void calculateTotalForCategoryTest(){

        Expense expHome = new Expense();
        expHome.setCategory("Home");
        expHome.setAmount(100);
        Expense expHome2 = new Expense();
        expHome2.setCategory("Home");
        expHome2.setAmount(89);
        Expense expCar = new Expense();
        expCar.setCategory("Car");
        expCar.setAmount(200);
        Expense expCar2 = new Expense();
        expCar2.setCategory("Car");
        expCar2.setAmount(99);

        List<Expense> expensesHome = Arrays.asList(expHome, expHome2);
        List<Expense> expensesCar = Arrays.asList(expCar2,expCar);


        ExpenseRepository repository = mock(ExpenseRepository.class);

//        when(repository.getExpensesByCategory("Food")).thenReturn(new ArrayList<Expense>());
//        when(repository.getExpensesByCategory("Sport")).thenReturn(new ArrayList<Expense>());
        when(repository.getExpensesByCategory(anyString())).thenReturn(new ArrayList<Expense>());
        when(repository.getExpensesByCategory("Home")).thenReturn(expensesHome);
        when(repository.getExpensesByCategory("Car")).thenReturn(expensesCar);

        ExpenseManager manager = new ExpenseManager(mock(FancyService.class), repository);
        var resHome = manager.calculateTotalForCategory("Home");
        var resCar = manager.calculateTotalForCategory("Car");
        var resFood = manager.calculateTotalForCategory("Food");
        var resSport = manager.calculateTotalForCategory("Sport");


        assertEquals(189, resHome);
        assertEquals(299, resCar);
        assertEquals(0, resFood);
        assertEquals(0, resSport);
    }


    @Test
    public void calculateTotalInDollarsTest() throws ConnectException {

        List<Expense> expenses = new ArrayList<>();
        ExpenseRepository repository = mock(ExpenseRepository.class);
        FancyService mockService = mock(FancyService.class);
        ExpenseManager mockManager = new ExpenseManager(mockService, repository);

        when(repository.getExpenses()).thenReturn(expenses);

        when(mockService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(new Answer<Double>() {
            @Override
            public Double answer(InvocationOnMock invocation) throws Throwable {
                return (Double) invocation.getArgument(0) / 4;
            }
        });
        assertEquals(expenses.stream().mapToDouble(expense -> expense.getAmount() / 4).sum(), mockManager.calculateTotalInDollars());
    }

}



