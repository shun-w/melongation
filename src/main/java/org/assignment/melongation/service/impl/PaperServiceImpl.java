package org.assignment.melongation.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.assignment.melongation.exception.MelongationException;
import org.assignment.melongation.mapper.PaperMapper;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.Question;
import org.assignment.melongation.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;

    @Override
    public void addPaper(Paper paper) {
        paperMapper.savePaper(paper);
    }

    /**
     * 进行分页
     *
     * @param currentPage 5页指定
     * @return
     */
    @Override
    public PageInfo<Paper> findAllPaper(int currentPage) {

        int pageSize = 5;
        PageHelper.startPage(currentPage, pageSize);

        List<Paper> papers = paperMapper.findAllPaper();

        PageInfo<Paper> pageInfo = new PageInfo<>(papers);

        return pageInfo;
    }

        @Override
        public PageInfo<Paper> findUserPaper(int currentPage,String username){
            int pageSize = 5;
            PageHelper.startPage(currentPage,pageSize);

            List<Paper> papers = paperMapper.findUserPaper(username);

            PageInfo<Paper> pgInfo = new PageInfo<>(papers);

            return pgInfo;
        }


    @Override
    public Paper findPaperById(int id) {
        return paperMapper.findPaperById(id);
    }

    @Override
    public void checkPaper(int id) {

        paperMapper.checkPaperById(id);
    }

    @Override
    public Paper getCheckedPaper(Integer paperId) {
        Paper paper = paperMapper.findPaperById(paperId);
        if (paper.getIsChecked() == false) {
            throw new MelongationException("该问卷正在等待管理员审核");
        } else if (paper == null) {
            throw new MelongationException("该问卷不存在");
        }
        return paper;
    }

    /**
     * 根据用户id查询问卷，并进行分页
     * @param pageNo 当前页面的页号
     * @return
     */
    @Override
    public PageInfo<Paper> findAllPaperByUser(int pageNo,int id) {
        int pageSize = 5;
        PageHelper.startPage(pageNo, pageSize);
        List<Paper> papers = paperMapper.selectPageByUserId(id);
        PageInfo<Paper> pageInfo = new PageInfo<>(papers);
        return pageInfo;
    }
}
