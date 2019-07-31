package com.sf.jkt.k.testex;

import com.sf.jkt.k.testex.mvc.MModel;
import com.sf.jkt.k.testex.mvc.ModelService;
import com.sf.jkt.k.util.SpringBootBaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTestJavaWithSpring extends SpringBootBaseTest {
    @Autowired
    ModelService modelService;

    @Test
    public void testMock() {
        List mockedList = mock(List.class);
        when(mockedList.add("one")).thenReturn(true);
        when(mockedList.size()).thenReturn(1);
        Assert.assertTrue(mockedList.add("one"));
        Assert.assertFalse(mockedList.add("two"));
        Assert.assertEquals(mockedList.size(), 1);
    }

    @Test
    public void  testMvc(){
        MModel model=modelService.getModel(1L);
        System.out.println(model);
        Assert.assertTrue(1L==model.id);
    }

}
