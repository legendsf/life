package com.sf.jkt.k.comp.javaagent.mysql;

import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * Created by dragon on 16/3/29.
 */
public class MysqlTransformer extends AbstractTransformer {

  public MysqlTransformer(String mysqlInterceptor) {
    super(mysqlInterceptor);
  }

  @Override
  protected DynamicType.Builder.MethodDefinition.ImplementationDefinition builderTransform(
          DynamicType.Builder<?> builder, ClassFileLocator.Compound compound) {
    return builder.method(isFinal().and(named("sqlQueryDirect"))
        .and(returns(named("com.mysql.jdbc.ResultSetInternalMethods"))));
  }

}
