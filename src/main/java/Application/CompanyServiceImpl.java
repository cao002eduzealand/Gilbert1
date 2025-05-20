package Application;

import Domain.Company;
import Infrastructure.CompanyRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyServiceImpl implements ServiceInterface<Company> {

    private final CompanyRepositoryImpl repository;

    public CompanyServiceImpl(CompanyRepositoryImpl repository){
        this.repository=repository;
    }

    @Override
    public Company save(Company company){
        return repository.save(company);
    }
    @Override
    public List<Company> findAll(){
        return repository.findAll();
    }

    @Override
    public void update(Company company){
        repository.update(company);
    }


    @Override
    public void delete(int id){
        repository.delete(id);
    }

    @Override
    public Company findById(int id){
        return repository.findById(id);
    }
}
