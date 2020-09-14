import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IBrand, Brand } from 'app/shared/model/brand.model';
import { BrandService } from './brand.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-brand-update',
  templateUrl: './brand-update.component.html',
})
export class BrandUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
    deleted: [],
    description: [],
    isActive: [null, [Validators.required]],
    createdAt: [],
    modifiedAt: [],
    createdById: [null, [Validators.maxLength(24)]],
    modifiedById: [null, [Validators.maxLength(24)]],
    nameEnUs: [null, [Validators.maxLength(255)]],
    descriptionEnUs: [],
    nameDeDe: [null, [Validators.maxLength(255)]],
    descriptionDeDe: [],
    code: [null, [Validators.maxLength(255)]],
    ownerUserId: [null, [Validators.maxLength(24)]],
    assignedUserId: [null, [Validators.maxLength(24)]],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected brandService: BrandService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ brand }) => {
      if (!brand.id) {
        const today = moment().startOf('day');
        brand.createdAt = today;
        brand.modifiedAt = today;
      }

      this.updateForm(brand);
    });
  }

  updateForm(brand: IBrand): void {
    this.editForm.patchValue({
      id: brand.id,
      name: brand.name,
      deleted: brand.deleted,
      description: brand.description,
      isActive: brand.isActive,
      createdAt: brand.createdAt ? brand.createdAt.format(DATE_TIME_FORMAT) : null,
      modifiedAt: brand.modifiedAt ? brand.modifiedAt.format(DATE_TIME_FORMAT) : null,
      createdById: brand.createdById,
      modifiedById: brand.modifiedById,
      nameEnUs: brand.nameEnUs,
      descriptionEnUs: brand.descriptionEnUs,
      nameDeDe: brand.nameDeDe,
      descriptionDeDe: brand.descriptionDeDe,
      code: brand.code,
      ownerUserId: brand.ownerUserId,
      assignedUserId: brand.assignedUserId,
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
    const brand = this.createFromForm();
    if (brand.id !== undefined) {
      this.subscribeToSaveResponse(this.brandService.update(brand));
    } else {
      this.subscribeToSaveResponse(this.brandService.create(brand));
    }
  }

  private createFromForm(): IBrand {
    return {
      ...new Brand(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      deleted: this.editForm.get(['deleted'])!.value,
      description: this.editForm.get(['description'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      modifiedAt: this.editForm.get(['modifiedAt'])!.value ? moment(this.editForm.get(['modifiedAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdById: this.editForm.get(['createdById'])!.value,
      modifiedById: this.editForm.get(['modifiedById'])!.value,
      nameEnUs: this.editForm.get(['nameEnUs'])!.value,
      descriptionEnUs: this.editForm.get(['descriptionEnUs'])!.value,
      nameDeDe: this.editForm.get(['nameDeDe'])!.value,
      descriptionDeDe: this.editForm.get(['descriptionDeDe'])!.value,
      code: this.editForm.get(['code'])!.value,
      ownerUserId: this.editForm.get(['ownerUserId'])!.value,
      assignedUserId: this.editForm.get(['assignedUserId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBrand>>): void {
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
}
