package org.assignment.melongation.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.Question;

import java.util.List;

public interface PaperService {


    void addPaper(Paper paper);


    PageInfo<Paper> findAllPaper (int i);

    Paper findPaperById(int id);

   void checkPaper(int id);

    Paper getCheckedPaper(Integer paperId);

    PageInfo<Paper> findAllPaperByUser(int pageNo,int id );
}
