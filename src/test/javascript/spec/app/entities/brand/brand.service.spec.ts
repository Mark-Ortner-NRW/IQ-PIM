import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BrandService } from 'app/entities/brand/brand.service';
import { IBrand, Brand } from 'app/shared/model/brand.model';

describe('Service Tests', () => {
  describe('Brand Service', () => {
    let injector: TestBed;
    let service: BrandService;
    let httpMock: HttpTestingController;
    let elemDefault: IBrand;
    let expectedResult: IBrand | IBrand[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BrandService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Brand(
        0,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            modifiedAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Brand', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            modifiedAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdAt: currentDate,
            modifiedAt: currentDate,
          },
          returnedFromService
        );

        service.create(new Brand()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Brand', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            deleted: true,
            description: 'BBBBBB',
            isActive: true,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            modifiedAt: currentDate.format(DATE_TIME_FORMAT),
            createdById: 'BBBBBB',
            modifiedById: 'BBBBBB',
            nameEnUs: 'BBBBBB',
            descriptionEnUs: 'BBBBBB',
            nameDeDe: 'BBBBBB',
            descriptionDeDe: 'BBBBBB',
            code: 'BBBBBB',
            ownerUserId: 'BBBBBB',
            assignedUserId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdAt: currentDate,
            modifiedAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Brand', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            deleted: true,
            description: 'BBBBBB',
            isActive: true,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            modifiedAt: currentDate.format(DATE_TIME_FORMAT),
            createdById: 'BBBBBB',
            modifiedById: 'BBBBBB',
            nameEnUs: 'BBBBBB',
            descriptionEnUs: 'BBBBBB',
            nameDeDe: 'BBBBBB',
            descriptionDeDe: 'BBBBBB',
            code: 'BBBBBB',
            ownerUserId: 'BBBBBB',
            assignedUserId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdAt: currentDate,
            modifiedAt: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Brand', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
