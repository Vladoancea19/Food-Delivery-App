package DbPersistence.Service.TakeAwayOrderService;

import DbPersistence.Dao.TakeAwayOrderDao.TakeawayOrderDao;
import DbPersistence.Dao.TakeAwayOrderDao.TakeawayOrderDaoImp;
import DbPersistence.Mapper.TakeAwayOrderMapper.TakeawayOrderEntityMapper;
import DbPersistence.Mapper.TakeAwayOrderMapper.TakeawayOrderMapper;
import DbPersistence.Entities.TakeawayOrderEntity.TakeawayOrderEntity;
import User.Order.TakeawayOrder.TakeawayOrder;

import java.util.List;
import java.util.function.Predicate;

public class TakeawayOrderServiceImp implements TakeawayOrderService {

    private final TakeawayOrderDao takeAwayOrderDao = new TakeawayOrderDaoImp();
    private final TakeawayOrderMapper takeAwayOrderMapper = new TakeawayOrderEntityMapper();

    @Override
    public TakeawayOrder create(TakeawayOrder enitity) {
        TakeawayOrderEntity takeAwayOrderEntity = takeAwayOrderMapper.modelToEntity(enitity);
        TakeawayOrderEntity savedTakeawayOrderEntity = this.takeAwayOrderDao.create(takeAwayOrderEntity);
        return enitity;
    }

    @Override
    public List<TakeawayOrder> read() {
        return null;
    }

    @Override
    public TakeawayOrder update(TakeawayOrder takeAwayOrder, Integer id) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    @Override
    public TakeawayOrder findById(Integer id) {
        return null;
    }

    @Override
    public List<TakeawayOrder> findAllBy(Predicate<TakeawayOrderEntity> predicate) {
        return null;
    }
}
