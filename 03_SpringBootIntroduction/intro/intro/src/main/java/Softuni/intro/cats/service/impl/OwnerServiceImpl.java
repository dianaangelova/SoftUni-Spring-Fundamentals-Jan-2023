package Softuni.intro.cats.service.impl;

import Softuni.intro.cats.model.dto.CreateOwnerDTO;
import Softuni.intro.cats.model.entity.CatEntity;
import Softuni.intro.cats.model.entity.OwnerEntity;
import Softuni.intro.cats.repository.OwnerRepository;
import Softuni.intro.cats.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    private OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void createOwner(CreateOwnerDTO createOwnerDTO) {

        OwnerEntity owner = new OwnerEntity();
        owner.setOwnerName(createOwnerDTO.getOwnerName());

        createOwnerDTO.getCatNames().
                forEach(name -> {
                    CatEntity cat = new CatEntity();
                    cat.setCatName(name);
                    cat.setOwner(owner);
                    owner.addCat(cat);
                });

        ownerRepository.save(owner);
    }


}
