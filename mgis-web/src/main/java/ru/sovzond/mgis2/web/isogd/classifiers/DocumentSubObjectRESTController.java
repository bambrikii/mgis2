package ru.sovzond.mgis2.web.isogd.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentObjectBean;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentSubObjectBean;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObject;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObject;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@RestController
@RequestMapping("/isogd/classifiers/documents/subobjects")
public class DocumentSubObjectRESTController implements Serializable {

    @Autowired
    private DocumentObjectBean documentObjectBean;

    @Autowired
    private DocumentSubObjectBean documentSubObjectBean;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Transactional
    public PageableContainer<DocumentSubObject> list(@RequestParam Long objectId, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
        DocumentObject documentObject = documentObjectBean.load(objectId);
        PageableContainer<DocumentSubObject> pager = documentSubObjectBean.list(documentObject, first, max);
        return new PageableContainer<>(pager.getList().stream().map(item ->
                        item.clone()
        ).collect(Collectors.toList()), pager.getCount());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @Transactional
    public DocumentSubObject save(@PathVariable("id") Long id, @RequestBody DocumentSubObject documentSubObject) {
        DocumentSubObject result;
        if (id == 0) {
            result = new DocumentSubObject();
        } else {
            result = documentSubObjectBean.load(id);
        }
        result.setCode(documentSubObject.getCode());
        result.setName(documentSubObject.getName());
        result.setDocumentObject(documentObjectBean.load(documentSubObject.getDocumentObject().getId()));
        documentSubObjectBean.save(result);
        return result.clone();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Transactional
    public DocumentSubObject read(@PathVariable Long id) {
        return documentSubObjectBean.load(id).clone();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional
    public void delete(@PathVariable Long id) {
        documentSubObjectBean.remove(documentSubObjectBean.load(id));
    }
}
