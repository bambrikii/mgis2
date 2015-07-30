package ru.sovzond.mgis2.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableBase;
import ru.sovzond.mgis2.isogd.Book;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.VolumeDao;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 28/06/15.
 */
@Service
public class VolumeBean {

    @Autowired
    private VolumeDao volumeDao;

    public Volume readVolume(Long id) {
        return volumeDao.findById(id);
    }

    public void save(Volume volume) {
        volumeDao.save(volume);
    }

    public void delete(Volume volume) {
        volumeDao.delete(volume);
    }

    public PageableContainer<Volume> pageVolumes(Book book, int first, int max) {
        PageableBase<Volume> filter = volumeDao.createFilter(book, first, max);
        List<Volume> list = volumeDao.list(filter).stream().map(volume -> volume.clone()).collect(Collectors.toList());
        return new PageableContainer<>(list, volumeDao.count(filter), first, max);
    }

}
