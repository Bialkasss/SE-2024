package put.io.testing.mocks;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import org.mockito.internal.matchers.Null;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

public class ExpenseRepositoryTest {

    @Test
    void loadExpenses() {
        IFancyDatabase DbMock = mock(FancyDatabase.class);
        when(DbMock.queryAll()).thenReturn(new ArrayList<>());

        var repository = new ExpenseRepository(DbMock);
        repository.loadExpenses();

        var ordered = inOrder(DbMock);
        ordered.verify(DbMock, times(1)).connect();
        ordered.verify(DbMock, times(1)).queryAll();
        ordered.verify(DbMock, times(1)).close();


        var expenses = repository.getExpenses();
        assertTrue(expenses.isEmpty());
    }

    @Test
    void loadSaveExpenses(){
        IFancyDatabase DbMock = mock(FancyDatabase.class);
        when(DbMock.queryAll()).thenReturn(new ArrayList<>());


        var repository = new ExpenseRepository(DbMock);

        for (int i = 0; i < 5 ; i++){
            repository.addExpense(new Expense());
        }
        repository.saveExpenses();
        var expenses = repository.getExpenses();
        assertEquals(5, expenses.size());

        var ordered = inOrder(DbMock);
        ordered.verify(DbMock, times(5)).persist(any(Expense.class));
    }
}
