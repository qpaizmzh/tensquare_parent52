package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entitys.PageResult;
import entitys.Result;
import entitys.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin//在微服务之间的调用需要用到跨域的注释来解决访问的问题
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;


    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
//        Result result = new Result(true, StatusCode.OK, "查询成功",labelService.getAll());
//        return result;
        List<Label> haha = labelService.getAll();
        return new Result(true, StatusCode.OK, "查询成功", haha);
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "labelId") String id) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result updateById(@PathVariable(value = "labelId") String id, @RequestBody Label label) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable(value = "labelId") String id) {
        labelService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result searchAll(@RequestBody Label label) {
        List<Label> list = labelService.searchAll(label);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchAll(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> list = labelService.searchAll(label,page,size);
        return new Result(true, StatusCode.OK, "查询成功",new PageResult<Label>(list.getTotalElements(),list.getContent()));
    }


}
