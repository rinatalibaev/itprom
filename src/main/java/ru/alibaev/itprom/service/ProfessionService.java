package ru.alibaev.itprom.service;

import org.springframework.stereotype.Service;
import ru.alibaev.itprom.entity.Profession;
import ru.alibaev.itprom.repository.ProfessionRepository;

import java.util.List;

@Service
public class ProfessionService {

    private ProfessionRepository professionRepository;

    public ProfessionService(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    public List<Profession> getAll() {
        return professionRepository.findAll();
    }


    public Profession get(Long professionId) {
        return professionRepository.getOne(professionId);
    }

    public void create(Profession profession) {
        professionRepository.save(profession);
    }

    public void update(Profession profession) {
        Profession professionFromDB = professionRepository.getOne(profession.getId());
        professionFromDB.setName(profession.getName());
        professionFromDB.setNote(profession.getNote());
        professionRepository.save(professionFromDB);
    }

    public void delete(Long professionId) {
        professionRepository.deleteById(professionId);
    }
}
