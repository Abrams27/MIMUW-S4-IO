package pl.uw.mim.io.lekizapteki.mappers.dto;


import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.models.medicine.MedicineDto;
import pl.uw.mim.io.lekizapteki.repositories.entities.MedicineEntity;

@UtilityClass
public class MedicinesDtoMapper {

  public List<MedicineDto> map(List<MedicineEntity> medicineEntityList) {
    return medicineEntityList.stream()
        .map(MedicinesDtoMapper::mapMedicineEntity)
        .collect(Collectors.toList());
  }

  private MedicineDto mapMedicineEntity(MedicineEntity medicineEntity) {
    return MedicineDto.builder()
        .ean(medicineEntity.getEan())
        .dose(medicineEntity.getDose().getDose())
        .name(medicineEntity.getName())
        .build();
  }
}
