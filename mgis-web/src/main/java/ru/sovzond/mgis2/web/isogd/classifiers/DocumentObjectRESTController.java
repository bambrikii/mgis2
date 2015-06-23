package ru.sovzond.mgis2.web.isogd.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentClassBean;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentObjectBean;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentSubObjectBean;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObject;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@RestController
@RequestMapping("/isogd/classifiers/documents/objects")
public class DocumentObjectRESTController implements Serializable {

    @Autowired
    private DocumentClassBean documentClassBean;

    @Autowired
    private DocumentObjectBean documentObjectBean;

    @Autowired
    private DocumentSubObjectBean documentSubObjectBean;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Transactional
    public PageableContainer<DocumentObject> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
        PageableContainer<DocumentObject> pager = documentObjectBean.list(first, max);
        return new PageableContainer<>(pager.getList().stream().map(item ->
                        item.clone()
        ).collect(Collectors.toList()), pager.getCount());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @Transactional
    public DocumentObject save(@PathVariable("id") Long id, @RequestBody DocumentObject documentObject) {
        DocumentObject result;
        if (id == 0) {
            result = new DocumentObject();
        } else {
            result = documentObjectBean.load(id);
        }
        result.setCode(documentObject.getCode());
        result.setName(documentObject.getName());
        result.setDocumentClass(documentClassBean.load(documentObject.getDocumentClass().getId()));
        if (documentObject.getDocumentSubObjects().size() > 0) {
            result.setDocumentSubObjects(documentSubObjectBean.load(documentObject.getDocumentSubObjects().stream().map(documentSubObject -> documentSubObject.getId()).collect(Collectors.toList())));
        } else {
            result.getDocumentSubObjects().clear();
        }
        documentObjectBean.save(result);
        return result.clone();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Transactional
    public DocumentObject read(@PathVariable Long id) {
        return documentObjectBean.load(id).clone();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional
    public void delete(@PathVariable Long id) {
        documentObjectBean.remove(documentObjectBean.load(id));
    }
}
