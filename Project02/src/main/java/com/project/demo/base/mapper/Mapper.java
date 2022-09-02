package com.project.demo.base.mapper;

import java.util.List;
import java.util.Set;

public interface Mapper<E, D>  {

    public List<D> map(List<E> sources);

    public Set<D> map(Set<E> sources);
    
    public D map(E source);
   
    public void map(D source, E destination);
}
