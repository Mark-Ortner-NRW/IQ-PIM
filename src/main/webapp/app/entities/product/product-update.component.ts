import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IProduct, Product } from 'app/shared/model/product.model';
import { ProductService } from './product.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IBrand } from 'app/shared/model/brand.model';
import { BrandService } from 'app/entities/brand/brand.service';

@Component({
  selector: 'jhi-product-update',
  templateUrl: './product-update.component.html',
})
export class ProductUpdateComponent implements OnInit {
  isSaving = false;
  brands: IBrand[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
    deleted: [],
    createdAt: [],
    modifiedAt: [],
    sku: [null, [Validators.maxLength(255)]],
    isActive: [null, [Validators.required]],
    type: [null, [Validators.maxLength(255)]],
    amount: [],
    createdById: [null, [Validators.maxLength(24)]],
    modifiedById: [null, [Validators.maxLength(24)]],
    productFamilyId: [null, [Validators.maxLength(24)]],
    nameEnUs: [null, [Validators.maxLength(255)]],
    nameDeDe: [null, [Validators.maxLength(255)]],
    productStatus: [null, [Validators.maxLength(255)]],
    price: [],
    currency: [null, [Validators.maxLength(255)]],
    priceCurrency: [null, [Validators.maxLength(255)]],
    taxId: [null, [Validators.maxLength(24)]],
    ean: [null, [Validators.maxLength(255)]],
    mpn: [null, [Validators.maxLength(255)]],
    packagingId: [null, [Validators.maxLength(24)]],
    uvp: [],
    tag: [],
    ownerUserId: [null, [Validators.maxLength(24)]],
    assignedUserId: [null, [Validators.maxLength(24)]],
    finalPrice: [],
    finalPriceCurrency: [null, [Validators.maxLength(255)]],
    longDescription: [],
    longDescriptionDeDe: [],
    longDescriptionEnUs: [],
    productSerieId: [null, [Validators.maxLength(24)]],
    data: [],
    catalogId: [null, [Validators.maxLength(24)]],
    basePriceAmount: [],
    packedAmount: [],
    imageId: [null, [Validators.maxLength(24)]],
    measuringUnitId: [null, [Validators.maxLength(24)]],
    deliveryStatus: [null, [Validators.maxLength(255)]],
    deliveryStatusDeDe: [null, [Validators.maxLength(255)]],
    deliveryStatusEnUs: [null, [Validators.maxLength(255)]],
    supplied: [],
    suppliedDeDe: [],
    suppliedEnUs: [],
    brandId: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected productService: ProductService,
    protected brandService: BrandService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ product }) => {
      if (!product.id) {
        const today = moment().startOf('day');
        product.createdAt = today;
        product.modifiedAt = today;
      }

      this.updateForm(product);

      this.brandService.query().subscribe((res: HttpResponse<IBrand[]>) => (this.brands = res.body || []));
    });
  }

  updateForm(product: IProduct): void {
    this.editForm.patchValue({
      id: product.id,
      name: product.name,
      deleted: product.deleted,
      createdAt: product.createdAt ? product.createdAt.format(DATE_TIME_FORMAT) : null,
      modifiedAt: product.modifiedAt ? product.modifiedAt.format(DATE_TIME_FORMAT) : null,
      sku: product.sku,
      isActive: product.isActive,
      type: product.type,
      amount: product.amount,
      createdById: product.createdById,
      modifiedById: product.modifiedById,
      productFamilyId: product.productFamilyId,
      nameEnUs: product.nameEnUs,
      nameDeDe: product.nameDeDe,
      productStatus: product.productStatus,
      price: product.price,
      currency: product.currency,
      priceCurrency: product.priceCurrency,
      taxId: product.taxId,
      ean: product.ean,
      mpn: product.mpn,
      packagingId: product.packagingId,
      uvp: product.uvp,
      tag: product.tag,
      ownerUserId: product.ownerUserId,
      assignedUserId: product.assignedUserId,
      finalPrice: product.finalPrice,
      finalPriceCurrency: product.finalPriceCurrency,
      longDescription: product.longDescription,
      longDescriptionDeDe: product.longDescriptionDeDe,
      longDescriptionEnUs: product.longDescriptionEnUs,
      productSerieId: product.productSerieId,
      data: product.data,
      catalogId: product.catalogId,
      basePriceAmount: product.basePriceAmount,
      packedAmount: product.packedAmount,
      imageId: product.imageId,
      measuringUnitId: product.measuringUnitId,
      deliveryStatus: product.deliveryStatus,
      deliveryStatusDeDe: product.deliveryStatusDeDe,
      deliveryStatusEnUs: product.deliveryStatusEnUs,
      supplied: product.supplied,
      suppliedDeDe: product.suppliedDeDe,
      suppliedEnUs: product.suppliedEnUs,
      brandId: product.brandId,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('innoqPimApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const product = this.createFromForm();
    if (product.id !== undefined) {
      this.subscribeToSaveResponse(this.productService.update(product));
    } else {
      this.subscribeToSaveResponse(this.productService.create(product));
    }
  }

  private createFromForm(): IProduct {
    return {
      ...new Product(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      modifiedAt: this.editForm.get(['modifiedAt'])!.value ? moment(this.editForm.get(['modifiedAt'])!.value, DATE_TIME_FORMAT) : undefined,
      sku: this.editForm.get(['sku'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      type: this.editForm.get(['type'])!.value,
      amount: this.editForm.get(['amount'])!.value,
      createdById: this.editForm.get(['createdById'])!.value,
      modifiedById: this.editForm.get(['modifiedById'])!.value,
      productFamilyId: this.editForm.get(['productFamilyId'])!.value,
      nameEnUs: this.editForm.get(['nameEnUs'])!.value,
      nameDeDe: this.editForm.get(['nameDeDe'])!.value,
      productStatus: this.editForm.get(['productStatus'])!.value,
      price: this.editForm.get(['price'])!.value,
      currency: this.editForm.get(['currency'])!.value,
      priceCurrency: this.editForm.get(['priceCurrency'])!.value,
      taxId: this.editForm.get(['taxId'])!.value,
      ean: this.editForm.get(['ean'])!.value,
      mpn: this.editForm.get(['mpn'])!.value,
      packagingId: this.editForm.get(['packagingId'])!.value,
      uvp: this.editForm.get(['uvp'])!.value,
      tag: this.editForm.get(['tag'])!.value,
      ownerUserId: this.editForm.get(['ownerUserId'])!.value,
      assignedUserId: this.editForm.get(['assignedUserId'])!.value,
      finalPrice: this.editForm.get(['finalPrice'])!.value,
      finalPriceCurrency: this.editForm.get(['finalPriceCurrency'])!.value,
      longDescription: this.editForm.get(['longDescription'])!.value,
      longDescriptionDeDe: this.editForm.get(['longDescriptionDeDe'])!.value,
      longDescriptionEnUs: this.editForm.get(['longDescriptionEnUs'])!.value,
      productSerieId: this.editForm.get(['productSerieId'])!.value,
      data: this.editForm.get(['data'])!.value,
      catalogId: this.editForm.get(['catalogId'])!.value,
      basePriceAmount: this.editForm.get(['basePriceAmount'])!.value,
      packedAmount: this.editForm.get(['packedAmount'])!.value,
      imageId: this.editForm.get(['imageId'])!.value,
      measuringUnitId: this.editForm.get(['measuringUnitId'])!.value,
      deliveryStatus: this.editForm.get(['deliveryStatus'])!.value,
      deliveryStatusDeDe: this.editForm.get(['deliveryStatusDeDe'])!.value,
      deliveryStatusEnUs: this.editForm.get(['deliveryStatusEnUs'])!.value,
      supplied: this.editForm.get(['supplied'])!.value,
      suppliedDeDe: this.editForm.get(['suppliedDeDe'])!.value,
      suppliedEnUs: this.editForm.get(['suppliedEnUs'])!.value,
      brandId: this.editForm.get(['brandId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduct>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IBrand): any {
    return item.id;
  }
}
