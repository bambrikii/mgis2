package ru.sovzond.mgis2.web.isogd.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentClassBean;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentObjectBean;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@RestController
@RequestMapping("/isogd/classifiers/documents/classes")
public class DocumentClassRESTController implements Serializable {

    @Autowired
    private DocumentClassBean documentClassBean;

    @Autowired
    private DocumentObjectBean documentObjectBean;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Transactional
    public PageableContainer<DocumentClass> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
        PageableContainer<DocumentClass> pager = documentClassBean.list(first, max);
        return new PageableContainer<>(pager.getList().stream().map(item ->
                        item.clone()
        ).collect(Collectors.toList()), pager.getCount());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Transactional
    public DocumentClass save(@PathVariable("id") Long id, @RequestBody DocumentClass documentClass) {
        DocumentClass result;
        if (id == 0) {
            result = new DocumentClass();
        } else {
            result = documentClassBean.load(id);
        }
        result.setCode(documentClass.getCode());
        result.setName(documentClass.getName());
        result.setHasCommonPart(documentClass.isHasCommonPart());
        result.setHasSpecialPart(documentClass.isHasSpecialPart());
        if (documentClass.getDocumentObjects().size() > 0) {
            result.setDocumentObjects(documentObjectBean.load(documentClass.getDocumentObjects().stream().map(item -> item.getId()).collect(Collectors.toList())));
        } else {
            result.getDocumentObjects().clear();
        }
        documentClassBean.save(result);
        return result.clone();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Transactional
    public DocumentClass read(@PathVariable Long id) {
        return documentClassBean.load(id).clone();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional
    public void delete(@PathVariable Long id) {
        documentClassBean.remove(documentClassBean.load(id));
    }
}
