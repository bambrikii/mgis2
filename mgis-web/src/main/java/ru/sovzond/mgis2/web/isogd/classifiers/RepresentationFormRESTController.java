package ru.sovzond.mgis2.web.isogd.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.business.PageableContainer;
import ru.sovzond.mgis2.isogd.business.classifiers.representation.RepresentationFormBean;
import ru.sovzond.mgis2.isogd.business.classifiers.representation.RepresentationFormatBean;
import ru.sovzond.mgis2.isogd.classifiers.documents.representation.RepresentationForm;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@RestController
@RequestMapping("/isogd/classifiers/documents/representations/forms")
@Scope("session")
public class RepresentationFormRESTController implements Serializable {

    @Autowired
    private RepresentationFormBean representationFormBean;

    @Autowired
    private RepresentationFormatBean representationFormatBean;


    @RequestMapping(value = "", method = RequestMethod.GET)
    @Transactional
    public PageableContainer<RepresentationForm> list(@RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max) {
        PageableContainer<RepresentationForm> pager = representationFormBean.list(first, max);
        return new PageableContainer<>(pager.getList().stream().map(representationForm ->
                        representationForm.clone()
        ).collect(Collectors.toList()), pager.getCount());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Transactional
    public RepresentationForm save(@PathVariable("id") Long id, @RequestBody RepresentationForm representationForm) {
        RepresentationForm representationForm2;
        if (id == 0) {
            representationForm2 = new RepresentationForm();
        } else {
            representationForm2 = representationFormBean.load(id);
        }
        representationForm2.setCode(representationForm.getCode());
        representationForm2.setName(representationForm.getName());
        if (representationForm.getRepresentationFormats().size() > 0) {
            representationForm2.setRepresentationFormats(representationFormatBean.load(representationForm.getRepresentationFormats().stream().map(representationFormat ->
                            representationFormat.getId()
            ).collect(Collectors.toList())));
        } else {
            representationForm2.getRepresentationFormats().clear();
        }
        representationFormBean.save(representationForm2);
        return representationForm2.clone();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Transactional
    public RepresentationForm read(@PathVariable Long id) {
        return representationFormBean.load(id).clone();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional
    public void delete(@PathVariable Long id) {
        representationFormBean.remove(representationFormBean.load(id));
    }
}
