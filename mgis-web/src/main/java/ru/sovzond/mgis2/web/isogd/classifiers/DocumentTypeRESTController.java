package ru.sovzond.mgis2.web.isogd.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.business.classifiers.DocumentTypeBean;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentType;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@RestController
@RequestMapping("/isogd/classifiers/documents/types")
@Scope("session")
public class DocumentTypeRESTController implements Serializable {
    @Autowired
    private DocumentTypeBean documentTypeBean;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Transactional
    public PageableContainer<DocumentType> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
        PageableContainer<DocumentType> pager = documentTypeBean.list(first, max);
        return new PageableContainer<>(pager.getList().stream().map(representationForm ->
                        representationForm.clone()
        ).collect(Collectors.toList()), pager.getCount());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Transactional
    public DocumentType save(@PathVariable("id") Long id, @RequestBody DocumentType documentType) {
        DocumentType documentType2;
        if (id == 0) {
            documentType2 = new DocumentType();
        } else {
            documentType2 = documentTypeBean.load(id);
        }
        documentType2.setCode(documentType.getCode());
        documentType2.setName(documentType.getName());
        documentTypeBean.save(documentType2);
        return documentType2.clone();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Transactional
    public DocumentType read(@PathVariable Long id) {
        return documentTypeBean.load(id).clone();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional
    public void delete(@PathVariable Long id) {
        documentTypeBean.remove(documentTypeBean.load(id));
    }
}
