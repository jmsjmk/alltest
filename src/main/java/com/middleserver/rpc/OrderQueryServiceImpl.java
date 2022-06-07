//package com.middleserver.rpc;
//
//import com.xstore.oas.constants.OrderConstants;
//import com.xstore.oas.constants.UmpConstants;
//import com.xstore.oas.service.domain.TaskLog;
//import com.xstore.oas.service.util.OrderUtil;
//import com.xstore.soa.order.OrderQueryService;
//import com.xstore.soa.sync.OrderDisCountSync;
//import com.xstore.soa.sync.OrderInfoSync;
//import com.xstore.soa.sync.OrderItemsDiscountSync;
//import com.jd.ump.profiler.CallerInfo;
//import com.jd.ump.profiler.proxy.Profiler;
//import com.xstore.soa.sync.RpcResult;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * jsf-接口服务实现
// * <p>
// * Created by mingku.jia on 2017/6/14.
// */
//@Service
//public class OrderQueryServiceImpl implements OrderQueryService {
//
//    private static final Logger logger = LoggerFactory.getLogger(OrderQueryServiceImpl.class);
//
//    @Resource
//    private OrderInfoService orderInfoService;
//
//    @Override
//    public RpcResult<OrderItemsDiscountSync> queryOrderItemDiscount(Long orderId) {
//        TaskLog taskLog;
//        String msg;
//        String code;
//        OrderItemsDiscountSync orderItemDiscountSync = null;
//        CallerInfo callerInfo = Profiler.registerInfo(UmpConstants.FRESH_OAS_QUERY_ORDER_ITEM_DISCOUNT_SERVICE,
//                UmpConstants.UMP_APP_NAME,
//                UmpConstants.UMP_DISABLE_HEART, UmpConstants.UMP_ENABLE_TP);
//        try {
//            OrderItemsDiscount orderItemsDiscount;
//            long L1 = System.currentTimeMillis();
//            if (null == orderId) {
//                logger.error("OrderId is null queryOrderItemDiscount return null!!!");
//                return OrderUtil.getErrorRpcResult("OrderId is null");
//            }
//            taskLog = orderInfoService.selectMaxVersionTaskLogByOrderId(orderId);
//            if (!orderInfoService.isValidSplitStatus(taskLog)) {
//                return OrderUtil.getRpcResult(taskLog, orderId);
//            }
//
//            orderItemsDiscount = orderInfoService.queryOrderItemsDiscount(orderId, taskLog.getVersion());
//            orderItemDiscountSync = OrderUtil.covertOrderItemsDiscount2Sync(orderItemsDiscount);
//            msg = OrderConstants.SUCCESS_MSG;
//            code = OrderConstants.SUCCESS_CODE;
//            long L2 = System.currentTimeMillis();
//            logger.info("OrderId[{}] time[{}]ms", orderId, (L2 - L1));
//        } catch (Exception e) {
//            logger.error("queryOrderItemDiscount is Exception. orderId={}", orderId, e);
//            msg = OrderConstants.EXPETION_MSG;
//            code = OrderConstants.ERROR_CODE;
//            Profiler.functionError(callerInfo);
//        } finally {
//            Profiler.registerInfoEnd(callerInfo);
//        }
//        return OrderUtil.getRpcResult(orderItemDiscountSync, code, msg);
//    }
//
//    @Override
//    public RpcResult<OrderInfoSync> queryOrderInfo(Long orderId) {
//        OrderInfoSync orderInfoSync = null;
//        TaskLog taskLog;
//        String msg;
//        String code;
//        CallerInfo callerInfo = Profiler.registerInfo(UmpConstants.FRESH_OAS_QUERY_ORDER_INFO_SERVICE,
//                UmpConstants.UMP_APP_NAME,
//                UmpConstants.UMP_DISABLE_HEART, UmpConstants.UMP_ENABLE_TP);
//        try {
//            long L1 = System.currentTimeMillis();
//            OrderInfo orderInfo;
//            if (null == orderId) {
//                logger.error("OrderId is null queryOrderInfo return null!!!");
//                return OrderUtil.getErrorRpcResult("OrderId is null");
//            }
//            taskLog = orderInfoService.selectMaxVersionTaskLogByOrderId(orderId);
//            if (!orderInfoService.isValidSplitStatus(taskLog)) {
//                return OrderUtil.getRpcResult(taskLog, orderId);
//            }
//            orderInfo = orderInfoService.queryOrderInfo(orderId, taskLog.getVersion());
//            orderInfoSync = OrderUtil.covertOrderInfo2Sync(orderInfo);
//            long L2 = System.currentTimeMillis();
//            logger.info("OrderId[{}] time[{}]ms", orderId, (L2 - L1));
//            msg = OrderConstants.SUCCESS_MSG;
//            code = OrderConstants.SUCCESS_CODE;
//        } catch (Exception e) {
//            logger.error("queryOrderInfo is Exception. orderId={}", orderId, e);
//            msg = OrderConstants.EXPETION_MSG;
//            code = OrderConstants.ERROR_CODE;
//            Profiler.functionError(callerInfo);
//        } finally {
//            Profiler.registerInfoEnd(callerInfo);
//        }
//        return OrderUtil.getRpcResult(orderInfoSync, code, msg);
//    }
//
//    /**
//     * 查询优惠信息
//     *
//     * @param orderId 订单号
//     * @return OrderDisCountSync
//     */
//    @Override
//    public RpcResult<OrderDisCountSync> queryOrderDiscount(Long orderId) {
//        OrderDisCountSync orderDisCountSync = null;
//        TaskLog taskLog;
//        String msg;
//        String code;
//        CallerInfo callerInfo = Profiler.registerInfo(UmpConstants.FRESH_OAS_QUERY_ORDER_DISCOUNT_SERVICE,
//                UmpConstants.UMP_APP_NAME,
//                UmpConstants.UMP_DISABLE_HEART, UmpConstants.UMP_ENABLE_TP);
//        try {
//            long L1 = System.currentTimeMillis();
//            OrderDiscount orderDiscount;
//            if (null == orderId) {
//                logger.error("OrderId is null queryOrderDiscount return null!!!");
//                return OrderUtil.getErrorRpcResult("OrderId is null");
//            }
//            taskLog = orderInfoService.selectMaxVersionTaskLogByOrderId(orderId);
//            if (!orderInfoService.isValidSplitStatus(taskLog)) {
//                return OrderUtil.getRpcResult(taskLog, orderId);
//            }
//            orderDiscount = orderInfoService.queryOrderDiscount(orderId, taskLog.getVersion());
//            orderDisCountSync = OrderUtil.covertOrderDiscount2Sync(orderDiscount);
//
//            long L2 = System.currentTimeMillis();
//            logger.info("OrderId[{}] time[{}]ms", orderId, (L2 - L1));
//            msg = OrderConstants.SUCCESS_MSG;
//            code = OrderConstants.SUCCESS_CODE;
//        } catch (Exception e) {
//            logger.error("queryOrderDiscount is Exception. orderId={}", orderId, e);
//            msg = OrderConstants.EXPETION_MSG;
//            code = OrderConstants.ERROR_CODE;
//            Profiler.functionError(callerInfo);
//        } finally {
//            Profiler.registerInfoEnd(callerInfo);
//        }
//        return OrderUtil.getRpcResult(orderDisCountSync, code, msg);
//    }
//}
