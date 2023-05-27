package az.lesson32.util;

import az.lesson32.repository.impl.GetDBInfoRepository;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetDbInfoUtil {

    GetDBInfoRepository getDBInfoRepository;

    public GetDbInfoUtil(GetDBInfoRepository getDBInfoRepository) {
        this.getDBInfoRepository = getDBInfoRepository;
    }
    public int getRowCunt() {
        return getDBInfoRepository.getRowCount();
    }

    public List<Integer> getId() {
        return null;
    }
}
