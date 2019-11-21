package org.assignment.melongation.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
     * @param currentPage
     *  5页指定
     * @return
     */
        @Override
        public PageInfo<Paper> findAllPaper(int currentPage) {

            int pageSize  = 5;
            PageHelper.startPage(currentPage, pageSize);

            List<Paper> papers   = paperMapper.findAllPaper();

            PageInfo<Paper> pageInfo = new PageInfo<>(papers);

            return pageInfo;
        }


    @Override
    public Paper findPaperById(int id) {
        return paperMapper.findPaperById(id);
    }

    @Override
    public void checkPaper(int id ) {

        paperMapper.checkPaperById(id);
    }
}
