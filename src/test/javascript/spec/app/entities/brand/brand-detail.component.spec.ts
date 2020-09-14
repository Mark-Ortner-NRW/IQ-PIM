import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { InnoqPimTestModule } from '../../../test.module';
import { BrandDetailComponent } from 'app/entities/brand/brand-detail.component';
import { Brand } from 'app/shared/model/brand.model';

describe('Component Tests', () => {
  describe('Brand Management Detail Component', () => {
    let comp: BrandDetailComponent;
    let fixture: ComponentFixture<BrandDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ brand: new Brand(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InnoqPimTestModule],
        declarations: [BrandDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BrandDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BrandDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load brand on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.brand).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
