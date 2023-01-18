package Softuni.intro.cats;

import Softuni.intro.cats.model.dto.CreateOwnerDTO;
import Softuni.intro.cats.model.entity.CatEntity;
import Softuni.intro.cats.model.entity.OwnerEntity;
import Softuni.intro.cats.service.OwnerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatDemo implements CommandLineRunner {

    private OwnerService ownerService;

    public CatDemo(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public void run(String... args) throws Exception {

        CreateOwnerDTO createOwnerDTO = new CreateOwnerDTO();

        createOwnerDTO.setOwnerName("Pesho");

        createOwnerDTO.setCatNames(List.of("Pipi", "Emi"));

        ownerService.createOwner(createOwnerDTO);
    }
}
