import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProductService } from 'app/entities/product/product.service';
import { IProduct, Product } from 'app/shared/model/product.model';

describe('Service Tests', () => {
  describe('Product Service', () => {
    let injector: TestBed;
    let service: ProductService;
    let httpMock: HttpTestingController;
    let elemDefault: IProduct;
    let expectedResult: IProduct | IProduct[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProductService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Product(
        0,
        'AAAAAAA',
        false,
        currentDate,
        currentDate,
        'AAAAAAA',
        false,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
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

      it('should create a Product', () => {
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

        service.create(new Product()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Product', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            deleted: true,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            modifiedAt: currentDate.format(DATE_TIME_FORMAT),
            sku: 'BBBBBB',
            isActive: true,
            type: 'BBBBBB',
            amount: 1,
            createdById: 'BBBBBB',
            modifiedById: 'BBBBBB',
            productFamilyId: 'BBBBBB',
            nameEnUs: 'BBBBBB',
            nameDeDe: 'BBBBBB',
            productStatus: 'BBBBBB',
            price: 1,
            currency: 'BBBBBB',
            priceCurrency: 'BBBBBB',
            taxId: 'BBBBBB',
            ean: 'BBBBBB',
            mpn: 'BBBBBB',
            packagingId: 'BBBBBB',
            uvp: 1,
            tag: 'BBBBBB',
            ownerUserId: 'BBBBBB',
            assignedUserId: 'BBBBBB',
            finalPrice: 1,
            finalPriceCurrency: 'BBBBBB',
            longDescription: 'BBBBBB',
            longDescriptionDeDe: 'BBBBBB',
            longDescriptionEnUs: 'BBBBBB',
            productSerieId: 'BBBBBB',
            data: 'BBBBBB',
            catalogId: 'BBBBBB',
            basePriceAmount: 1,
            packedAmount: 1,
            imageId: 'BBBBBB',
            measuringUnitId: 'BBBBBB',
            deliveryStatus: 'BBBBBB',
            deliveryStatusDeDe: 'BBBBBB',
            deliveryStatusEnUs: 'BBBBBB',
            supplied: 'BBBBBB',
            suppliedDeDe: 'BBBBBB',
            suppliedEnUs: 'BBBBBB',
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

      it('should return a list of Product', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            deleted: true,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            modifiedAt: currentDate.format(DATE_TIME_FORMAT),
            sku: 'BBBBBB',
            isActive: true,
            type: 'BBBBBB',
            amount: 1,
            createdById: 'BBBBBB',
            modifiedById: 'BBBBBB',
            productFamilyId: 'BBBBBB',
            nameEnUs: 'BBBBBB',
            nameDeDe: 'BBBBBB',
            productStatus: 'BBBBBB',
            price: 1,
            currency: 'BBBBBB',
            priceCurrency: 'BBBBBB',
            taxId: 'BBBBBB',
            ean: 'BBBBBB',
            mpn: 'BBBBBB',
            packagingId: 'BBBBBB',
            uvp: 1,
            tag: 'BBBBBB',
            ownerUserId: 'BBBBBB',
            assignedUserId: 'BBBBBB',
            finalPrice: 1,
            finalPriceCurrency: 'BBBBBB',
            longDescription: 'BBBBBB',
            longDescriptionDeDe: 'BBBBBB',
            longDescriptionEnUs: 'BBBBBB',
            productSerieId: 'BBBBBB',
            data: 'BBBBBB',
            catalogId: 'BBBBBB',
            basePriceAmount: 1,
            packedAmount: 1,
            imageId: 'BBBBBB',
            measuringUnitId: 'BBBBBB',
            deliveryStatus: 'BBBBBB',
            deliveryStatusDeDe: 'BBBBBB',
            deliveryStatusEnUs: 'BBBBBB',
            supplied: 'BBBBBB',
            suppliedDeDe: 'BBBBBB',
            suppliedEnUs: 'BBBBBB',
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

      it('should delete a Product', () => {
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
