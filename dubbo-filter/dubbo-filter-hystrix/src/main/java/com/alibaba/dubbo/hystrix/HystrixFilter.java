/*
 * Copyright (C), 2014-2015, 江苏乐博国际投资发展有限公司
 * FileName: HystrixFilter
 * Author:   Lucky
 * Date:     2017/11/06 17:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.alibaba.dubbo.hystrix;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.support.DubboHystrixCommand;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author Lucky
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Activate(group = Constants.CONSUMER)
public class HystrixFilter implements Filter {
    
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        DubboHystrixCommand command = new DubboHystrixCommand(invoker, invocation);
        return command.execute();
    }
}
