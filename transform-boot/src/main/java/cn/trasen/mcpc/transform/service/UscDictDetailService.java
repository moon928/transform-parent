package cn.trasen.mcpc.transform.service;

import cn.trasen.mcpc.transform.model.UscDictDetail;

import java.util.List;

/**
 * @program: project-parent
 * @description: 字典明细Service
 * @author: yan_zt
 * @create: 2020-06-23 16:30
 */
public interface UscDictDetailService {
    /**
     * 获取字典明细列表
     *
     * @param catalogCode
     * @return
     * @author zhaowenbin
     */
    List<UscDictDetail> list(String catalogCode);
}
