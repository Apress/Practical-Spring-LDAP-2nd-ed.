package com.apress.book.ldap.transactions;

import java.util.List;

import com.apress.book.ldap.domain.Patron;
import com.apress.book.ldap.repository.PatronDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("patronService")
@Transactional
public class PatronServiceImpl implements PatronService {

    private static final Logger logger = LoggerFactory.getLogger(PatronServiceImpl.class);

    private PatronDao patronDao;

    public PatronServiceImpl(@Autowired @Qualifier("patronDao") PatronDao patronDao) {
        this.patronDao = patronDao;
    }

    @Override
    public void create(Patron patron) {
        logger.info("Beginning the transaction");
        patronDao.create(patron);
        logger.info("Ending the patron creation");
        throw new RuntimeException(); // Will roll back the transaction
    }

    public void create(List<Patron> patronList) {
        logger.info("Starting the transaction .....");

        for (Patron patron : patronList) {
            patronDao.create(patron);
        }

        logger.info("Ending the transaction");

        throw new RuntimeException();
    }

    public void delete(String id) {
        patronDao.delete(id);
        throw new RuntimeException(); // Need this to simulate a rollback
    }

    public void update(Patron patron) {
        patronDao.update(patron);
    }

    public Patron find(String id) {

        return patronDao.find(id);
    }
}