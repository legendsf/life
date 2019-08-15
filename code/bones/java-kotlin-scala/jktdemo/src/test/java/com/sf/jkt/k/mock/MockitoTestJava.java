//package com.sf.jkt.k.mock;
//
//import com.sf.jkt.k.service.mvc.MModel;
//import com.sf.jkt.k.service.mvc.ModelDao;
//import com.sf.jkt.k.service.mvc.ModelService;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.unitils.util.ReflectionUtils;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
///**
// * unitils :
// * https://www.jianshu.com/p/c9291422b09c
// */
//public class MockitoTestJava {
//    @InjectMocks
//    private ModelService modelService = new ModelService();
//    @Mock
//    private ModelDao modelDao;
//
//    private ModelService modelService1 = new ModelService();
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        ReflectionUtils.setFieldValue(modelService1, "modelDao", modelDao);
//        when(modelDao.getModel(any(Long.class))).thenReturn(new MModel(10L, "gsf"));
//    }
//
//    @Test
//    public void testContextLoads() {
//        MModel model = modelService.getModel(4L);
//        Assert.assertTrue(10L == model.id);
//        MModel model1 = modelService1.getModel(4L);
//        Assert.assertTrue(10L == model1.id);
//    }
//}
