package org.simpleflatmapper.test.map.mapper;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.Test;
import org.simpleflatmapper.map.EnumerableMapper;
import org.simpleflatmapper.reflect.ReflectionService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ECCollectionMapperTest {

    @Test
    public void testMutableList() throws Exception {

        AbstractMapperBuilderTest.SampleMapperBuilder<MutableA> builderA = new AbstractMapperBuilderTest.SampleMapperBuilder<MutableA>(ReflectionService.newInstance(false).getClassMeta(MutableA.class));

        builderA.addKey("id").addMapping("bs_v");

        EnumerableMapper<Object[][], MutableA, ?> mapper =
                builderA.mapper();

        MutableA a = mapper.iterator(new Object[][]{{1, "v1"}, {1, "v2"}}).next();

        assertEquals(Lists.mutable.of(new B("v1"), new B("v2")), a.bs);

        assertEquals(1, a.id);


    }

    @Test
    public void testImmutableList() throws Exception {

        AbstractMapperBuilderTest.SampleMapperBuilder<ImmutableA> builderA = 
                new AbstractMapperBuilderTest.SampleMapperBuilder<ImmutableA>(ReflectionService.newInstance(false).getClassMeta(ImmutableA.class));

        builderA.addKey("id").addMapping("bs_v");

        EnumerableMapper<Object[][], ImmutableA, ?> mapper =
                builderA.mapper();

        ImmutableA a = mapper.iterator(new Object[][]{{1, "v1"}, {1, "v2"}}).next();

        assertEquals(Lists.immutable.of(new B("v1"), new B("v2")), a.bs);
        assertEquals(1, a.id);


    }

    @Test
    public void testImmutableListObjectWithSetter() throws Exception {

        AbstractMapperBuilderTest.SampleMapperBuilder<ImmutableAA> builderA =
                new AbstractMapperBuilderTest.SampleMapperBuilder<ImmutableAA>(ReflectionService.newInstance(false).getClassMeta(ImmutableAA.class));

        builderA.addKey("id").addMapping("bs_v");

        EnumerableMapper<Object[][], ImmutableAA, ?> mapper =
                builderA.mapper();

        ImmutableAA a = mapper.iterator(new Object[][]{{1, "v1"}, {1, "v2"}}).next();

        assertEquals(Lists.immutable.of(new B("v1"), new B("v2")), a.bs);
        assertEquals(1, a.id);


    }

    @Test
    public void testImmutableListObjectMixSetter() throws Exception {

        AbstractMapperBuilderTest.SampleMapperBuilder<ImmutableAAA> builderA =
                new AbstractMapperBuilderTest.SampleMapperBuilder<ImmutableAAA>(ReflectionService.newInstance(false).getClassMeta(ImmutableAAA.class));

        builderA.addKey("id").addMapping("bs_v");

        EnumerableMapper<Object[][], ImmutableAAA, ?> mapper =
                builderA.mapper();

        ImmutableAAA a = mapper.iterator(new Object[][]{{1, "v1"}, {1, "v2"}}).next();

        assertEquals(Lists.immutable.of(new B("v1"), new B("v2")), a.bs);
        assertEquals(1, a.id);

    }

    @Test
    public void testImmutableWrapperList() throws Exception {

        AbstractMapperBuilderTest.SampleMapperBuilder<ImmutableAWrapper> builderA =
                new AbstractMapperBuilderTest.SampleMapperBuilder<ImmutableAWrapper>(ReflectionService.newInstance(false).getClassMeta(ImmutableAWrapper.class));

        builderA.addKey("a_id").addMapping("a_bs_v");

        EnumerableMapper<Object[][], ImmutableAWrapper, ?> mapper =
                builderA.mapper();

        ImmutableAWrapper a = mapper.iterator(new Object[][]{{1, "v1"}, {1, "v2"}}).next();

        assertEquals(Lists.immutable.of(new B("v1"), new B("v2")), a.a.bs);
        assertEquals(1, a.a.id);

    }
    
    public static class ImmutableAWrapper {
        private final String str;
        private final ImmutableA a;

        public ImmutableAWrapper(String str, ImmutableA a) {
            this.str = str;
            this.a = a;
        }

        public String getStr() {
            return str;
        }

        public ImmutableA getA() {
            return a;
        }
    }

    public static class ImmutableA {
        private final int id;
        private final ImmutableList<B> bs;

        public ImmutableA(int id, ImmutableList<B> bs) {
            this.id = id;
            this.bs = bs;
        }

        public ImmutableList getBs() {
            return bs;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "A{" +
                    "id=" + id +
                    ", bs=" + bs +
                    '}';
        }

    }

    public static class ImmutableAA {
        private int id;
        private final ImmutableList<B> bs;

        public ImmutableAA(int id, ImmutableList<B> bs) {
            this.id = id;
            this.bs = bs;
        }

        public ImmutableList getBs() {
            return bs;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "A{" +
                    "id=" + id +
                    ", bs=" + bs +
                    '}';
        }

    }
    public static class ImmutableAAA {
        private int id;
        private final ImmutableList<B> bs;

        public ImmutableAAA(ImmutableList<B> bs) {
            this.bs = bs;
        }

        public ImmutableList getBs() {
            return bs;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "A{" +
                    "id=" + id +
                    ", bs=" + bs +
                    '}';
        }

    }

    public static class MutableA {
        private final int id;
        private final MutableList<B> bs;

        public MutableA(int id, MutableList<B> bs) {
            this.id = id;
            this.bs = bs;
        }

        public MutableList getBs() {
            return bs;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "A{" +
                    "id=" + id +
                    ", bs=" + bs +
                    '}';
        }
    }
    
    public static class B {
        private final String v;

        public B(String v) {
            this.v = v;
        }


        @Override
        public String toString() {
            return "B{" +
                    "v='" + v + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            B b = (B) o;

            return v != null ? v.equals(b.v) : b.v == null;
        }

        @Override
        public int hashCode() {
            return v != null ? v.hashCode() : 0;
        }
    }

}