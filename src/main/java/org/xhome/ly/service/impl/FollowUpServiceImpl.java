package org.xhome.ly.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhome.ly.bean.FollowUp;
import org.xhome.ly.common.QueryBase;
import org.xhome.ly.common.Status;
import org.xhome.ly.mapper.FollowUpMapper;
import org.xhome.ly.service.FollowUpService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by onglchen
 * on 14-11-29.
 */
@Service
public class FollowUpServiceImpl  implements FollowUpService {

    private Log logger = LogFactory.getLog(FollowUpServiceImpl.class);

    @Autowired
    private FollowUpMapper followUpMapper;

    /**
     *
     * @param followUp
     * @return  0 成功  1 失败
     */
    @Override
    public int add(FollowUp followUp) {
        if (followUpMapper.insert(followUp) > 0) {
            logger.debug("添加随访:" + followUp.getId() + " 成功");
            return Status.SUCCESS;
        }
        logger.warn("添加随访:" + followUp.getId() + " 失败");
        return Status.ERROR;
    }


    /**
     *
     * @param followUp
     * @return  0 成功  1 失败  7 不存在
     */
    @Override
    public int update(FollowUp followUp) {
        FollowUp fol = followUpMapper.selectByPrimaryKey(followUp.getId());
        if(fol == null){
            logger.warn("尝试更新随访，但是随访不存在");
            return Status.NOT_EXISTS;
        }
        followUpMapper.updateByPrimaryKeySelective(followUp);
        logger.debug("修改随访："  + followUp.getId() + " 成功");
        return Status.SUCCESS;
    }

    /**
     *
     * @param followUp
     * @return  0 成功  1 失败  7 不存在
     */
    @Override
    public int delete(FollowUp followUp) {
        FollowUp fol=followUpMapper.selectByPrimaryKey(followUp.getId());
        if(fol == null){
            logger.warn("尝试删除随访，但是随访不存在");
            return Status.NOT_EXISTS;
        }
        followUpMapper.deleteByPrimaryKey(followUp.getId());
        logger.debug("删除随访："  + followUp.getId() + " 成功");
        return Status.SUCCESS;
    }

    /**
     *
     * @param id
     * @return  options
     */
    @Override
    public FollowUp get(int id) {
        FollowUp followUp = followUpMapper.selectByPrimaryKey(id);
        if (followUp == null) {
            logger.warn("随访 ID:" + id + " 不存在");
        } else {
            logger.debug("获取随访ID:" + id + " 成功");
        }
        return followUp;
    }

    /**
     *
     * @param queryBase
     */
    @Override
    public void query(QueryBase queryBase) {
        if (logger.isDebugEnabled()) {
            logger.debug("根据参数 " + queryBase.getParameters() + "  查询");
        }
        queryBase.setResults(followUpMapper.queryFollowUps(queryBase));
        queryBase.setTotalRow(followUpMapper.countFollowUps(queryBase));
    }

    public List<FollowUp> getByInterrogationRecordId(long interrogationRecordId){
        QueryBase queryBase = new QueryBase();
        queryBase.addParameter("interrogationRecordId",interrogationRecordId);
        List<FollowUp> followUps = new ArrayList<FollowUp>();
        followUps = followUpMapper.queryFollowUps(queryBase);

        return followUps;
    }
}