package pl.uw.mim.io.lekizapteki.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.io.lekizapteki.excel.parser.mappers.entities.MedicineEntityMapper;
import pl.uw.mim.io.lekizapteki.excel.parser.models.Medicine;
import pl.uw.mim.io.lekizapteki.exceptions.WrongMedicineForDiseaseException;
import pl.uw.mim.io.lekizapteki.repositories.DiseaseRepository;
import pl.uw.mim.io.lekizapteki.repositories.DoseRepository;
import pl.uw.mim.io.lekizapteki.repositories.IngredientRepository;
import pl.uw.mim.io.lekizapteki.repositories.MedicineRepository;
import pl.uw.mim.io.lekizapteki.repositories.entities.DiseaseEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.MedicineEntity;

@Service
@AllArgsConstructor
public class MedicineService {

  private MedicineRepository medicineRepository;
  private DiseaseService diseaseService;

  private DoseRepository doseRepository;
  private DiseaseRepository diseaseRepository;
  private IngredientRepository ingredientRepository;

  public List<MedicineEntity> getMedicinesForDiseaseId(Long diseaseId) {
    DiseaseEntity diseaseEntity = diseaseService.getDiseaseWithIdOrThrow(diseaseId);

    return medicineRepository
        .findAllByDisease(diseaseEntity);
  }

  public List<MedicineEntity> getIdenticalMedicines(String ean, Long diseaseId) {
    MedicineEntity medicineEntity = getMedicineWithEanAndDiseaseIdOrThrow(ean, diseaseId);
return List.of();
//    return medicineRepository
//        .findAllByIngredientInAndDoseInAndDiseaseIn(
//            ingredientRepository.findAllByName(medicineEntity.getIngredient().getName()),
//            doseRepository.findAllByDose(medicineEntity.getDose().getDose()),
//            diseaseRepository.findAllByName(medicineEntity.getDisease().getName()));
  }

  public MedicineEntity getMedicineWithEanAndDiseaseIdOrThrow(String ean, Long diseaseId) {
    DiseaseEntity diseaseEntity = diseaseService.getDiseaseWithIdOrThrow(diseaseId);

    return medicineRepository
        .findByEanAndDisease(ean, diseaseEntity)
        .orElseThrow(WrongMedicineForDiseaseException::new);
  }


  public void saveMedicinesToRepository(List<Medicine> medicines) {
    medicines.stream()
        .map(medicine -> MedicineEntityMapper.map(medicine, getDiseaseWithNameOrCreateNew(medicine)))
        .forEach(medicineRepository::save);
  }

  private DiseaseEntity getDiseaseWithNameOrCreateNew(Medicine medicine) {
    return diseaseService
        .getDiseaseWithNameOrCreateNew(medicine.getDisease());
  }

}
