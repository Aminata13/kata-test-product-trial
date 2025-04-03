import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MessageService} from "primeng/api";
import {CommonModule} from "@angular/common";
import {InputTextModule} from "primeng/inputtext";
import {InputTextareaModule} from "primeng/inputtextarea";
import {ButtonModule} from "primeng/button";
import {ToastModule} from "primeng/toast";
import {CardModule} from "primeng/card";

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [
      CommonModule,
      ReactiveFormsModule,
      InputTextModule,
      InputTextareaModule,
      ButtonModule,
      ToastModule,
      CardModule
  ],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent {
    private fb = inject(FormBuilder);
    private messageService = inject(MessageService);

    contactForm: FormGroup = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
        message: ['', [Validators.required, Validators.maxLength(300)]]
    });

    get messageLength(): number {
        return this.contactForm.get('message')?.value?.length || 0;
    }

    onSubmit(){
        if (this.contactForm.valid) {

            this.messageService.add({
                severity: 'success',
                summary: 'Succès',
                detail: 'Demande de contact envoyée avec succès'
            });

            this.contactForm.reset();
        }
    }
}
